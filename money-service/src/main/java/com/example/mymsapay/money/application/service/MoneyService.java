package com.example.mymsapay.money.application.service;

import com.example.mymsapay.common.ChargingMoneyTask;
import com.example.mymsapay.common.SubTask;
import com.example.mymsapay.common.UseCase;
import com.example.mymsapay.money.adapter.out.persistence.MemberMoneyEntity;
import com.example.mymsapay.money.adapter.out.persistence.MemberMoneyMapper;
import com.example.mymsapay.money.adapter.out.persistence.MoneyChargingEntity;
import com.example.mymsapay.money.adapter.out.persistence.MoneyChargingMapper;
import com.example.mymsapay.money.application.port.in.MoneyChargingCommand;
import com.example.mymsapay.money.application.port.in.MoneyUseCase;
import com.example.mymsapay.money.application.port.out.ChargingMoneyPort;
import com.example.mymsapay.money.application.port.out.GetMembershipPort;
import com.example.mymsapay.money.domain.ChargingMoneyStatus;
import com.example.mymsapay.money.domain.MemberMoney;
import com.example.mymsapay.money.domain.MoneyCharging;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@UseCase
@RequiredArgsConstructor
public class MoneyService implements MoneyUseCase {
    private final ChargingMoneyPort chargingMoneyPort;
    private final MoneyChargingMapper moneyChargingMapper;
    private final MemberMoneyMapper memberMoneyMapper;

    private final GetMembershipPort membershipPort;


    @Override
    public MoneyCharging chargeMoney(MoneyChargingCommand command) {

        // 1. 고객의 정보가 정상인지 확인
        membershipPort.getMembership(command.getTargetMembershipId());

        // 2. 고객의 연동된 계좌가 있는지, 고객의 연동된 계좌의 잔액이 충분한지 확인

        // 3. 우리의 법인 계좌 상태가 정상인지 확인 (고객의 실제 돈을 우리의 법인 계좌 송금 후 머니를 충전시켜주는 개념)

        // 4. 요청 상태인 충전 생성
        MemberMoneyEntity memberMoneyEntity = chargingMoneyPort.findMemberMoneyEntityByMembershipId(command.getTargetMembershipId());
        MoneyChargingEntity moneyChargingEntity = chargingMoneyPort.chargeMoney(new MoneyCharging.TargetMembershipId(command.getTargetMembershipId()), new MoneyCharging.ChargingMoneyAmount(command.getChargingMoneyAmount()), ChargingMoneyStatus.REQUESTED);

        // 5. 고객의 계좌 -> 페이 법인 계좌

        // 6. 정상 실패 처리 (일단은 성공이라 가정하고 요청 -> 성공으로 처리)
        chargingMoneyPort.chargeBalance(new MemberMoney.MemberMoneyId(memberMoneyEntity.getMemberMoneyId()), command.getChargingMoneyAmount());

        moneyChargingEntity.setChargingMoneyStatus(ChargingMoneyStatus.SUCCEED);
        chargingMoneyPort.updateMoneyCharging(moneyChargingEntity);


        return moneyChargingMapper.entityToDomain(moneyChargingEntity);
    }

    @Override
    public MoneyCharging chargeMoneyAsync(MoneyChargingCommand command) {
        // Subtask
        // 각 서비스에 특정 membershipId 로 Validation 을 하기위한 Task.

        // 1. Subtask, Task
        SubTask validMemberTask = SubTask.builder()
                .subTaskName("validMemberTask : " + "멤버십 유효성 검사")
                .membershipID(command.getTargetMembershipId())
                .taskType(SubTask.TaskType.MEMBERSHIP)
                .status(SubTask.Status.READY)
                .build();

        // Banking Sub task
        // Banking Account Validation
        SubTask validBankingAccountTask = SubTask.builder()
                .subTaskName("validBankingAccountTask : " + "뱅킹 계좌 유효성 검사")
                .membershipID(command.getTargetMembershipId())
                .taskType(SubTask.TaskType.BANKING)
                .status(SubTask.Status.READY)
                .build();

        // Amount Money Firmbanking --> 무조건 ok 받았다고 가정.

        List<SubTask> subTaskList = new ArrayList<>();
        subTaskList.add(validMemberTask);
        subTaskList.add(validBankingAccountTask);

        ChargingMoneyTask task = ChargingMoneyTask.builder()
                .taskID(UUID.randomUUID().toString())
                .taskName("Increase Money Task / 머니 충전 Task")
                .subTaskList(subTaskList)
                .moneyAmount(command.getAmount())
                .membershipID(command.getTargetMembershipId())
                .toBankName("fastcampus")
                .build();

        // 2. Kafka Cluster Produce
        // Task Produce
        sendChargingMoneyTaskPort.sendChargingMoneyTaskPort(task);
        countDownLatchManager.addCountDownLatch(task.getTaskID());

        // 3. Wait
        try {
            countDownLatchManager.getCountDownLatch(task.getTaskID()).await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // 3-1. task-consumer
        //  등록된 sub-task, status 모두 ok -> task 결과를 Produce

        // 4. Task Result Consume
        // 받은 응답을 다시, countDownLatchManager 를 통해서 결과 데이터를 받아야 해요.
        String result = countDownLatchManager.getDataForKey(task.getTaskID());
        if (result.equals("success")) {
            // 4-1. Consume ok, Logic
            MemberMoneyJpaEntity memberMoneyJpaEntity = increaseMoneyPort.increaseMoney(
                    new MemberMoney.MembershipId(command.getTargetMembershipId())
                    , command.getAmount());

            if (memberMoneyJpaEntity != null) {
                return mapper.mapToDomainEntity(increaseMoneyPort.createMoneyChangingRequest(
                                new MoneyChangingRequest.TargetMembershipId(command.getTargetMembershipId()),
                                new MoneyChangingRequest.MoneyChangingType(1),
                                new MoneyChangingRequest.ChangingMoneyAmount(command.getAmount()),
                                new MoneyChangingRequest.MoneyChangingStatus(1),
                                new MoneyChangingRequest.Uuid(UUID.randomUUID().toString())
                        )
                );
            }
        } else {
            // 4-2. Consume fail, Logic
            return null;
        }
        // 5. Consume ok, Logic
        return null;
    }

    @Override
    public MemberMoney createMoneyAccount(Long membershipId) {
        return memberMoneyMapper.entityToDomain(chargingMoneyPort.createMemberMoney(membershipId));
    }


}
