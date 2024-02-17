package com.example.mymsapay.money.application.service;

import com.example.mymsapay.UseCase;
import com.example.mymsapay.money.adapter.out.persistence.MoneyChargingEntity;
import com.example.mymsapay.money.adapter.out.persistence.MoneyChargingMapper;
import com.example.mymsapay.money.application.port.in.MoneyChargingCommand;
import com.example.mymsapay.money.application.port.in.MoneyUseCase;
import com.example.mymsapay.money.application.port.out.ChargingMoneyPort;
import com.example.mymsapay.money.domain.ChargingMoneyStatus;
import com.example.mymsapay.money.domain.MoneyCharging;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class MoneyService implements MoneyUseCase {
    private final ChargingMoneyPort chargingMoneyPort;
    private final MoneyChargingMapper mapper;

    @Override
    public MoneyCharging chargeMoney(MoneyChargingCommand command) {

        // 1. 고객의 정보가 정상인지 확인

        // 2. 고객의 연동된 계좌가 있는지, 고객의 연동된 계좌의 잔액이 충분한지 확인

        // 3. 우리의 법인 계좌 상태가 정상인지 확인 (고객의 실제 돈을 우리의 법인 계좌 송금 후 머니를 충전시켜주는 개념)

        // 4. 요청 상태인 충전 생성
        MoneyChargingEntity entity = chargingMoneyPort.chargeMoney(new MoneyCharging.TargetMembershipId(command.getTargetMembershipId()), new MoneyCharging.ChargingMoneyAmount(command.getChargingMoneyAmount()), ChargingMoneyStatus.REQUESTED);

        // 5. 고객의 계좌 -> 페이 법인 계좌

        // 6. 정상 실패 처리 (일단은 성공이라 가정하고 요청 -> 성공으로 처리)
        // 멤버의 머니값 증액도 필요한데
        entity.setChargingMoneyStatus(ChargingMoneyStatus.SUCCEED);
        return mapper.entityToDomain(entity);


    }
}
