package com.example.mymsapay.money.adapter.in.web;

import com.example.mymsapay.WebAdapter;
import com.example.mymsapay.money.adapter.in.web.dto.MoneyChargingRequest;
import com.example.mymsapay.money.application.port.in.MoneyChargingCommand;
import com.example.mymsapay.money.application.port.in.MoneyUseCase;
import com.example.mymsapay.money.domain.MemberMoney;
import com.example.mymsapay.money.domain.MoneyCharging;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@WebAdapter
@RequiredArgsConstructor
@RequestMapping("/banking")
public class MoneyController {

    private final MoneyUseCase moneyUseCase;

    @PostMapping(path = "/money/account/{membershipId}")
    ResponseEntity<MemberMoney> createMoneyAccount(@PathVariable Long membershipId) {
        return ResponseEntity.ok(moneyUseCase.createMoneyAccount(membershipId));
    }

    @PostMapping(path = "/money")
    ResponseEntity<MoneyCharging> chargeMoney(@RequestBody MoneyChargingRequest request) {
        // 먼저 Request가 들어오고

        // request 를 command로 바꿔준다 (중간을 추상화) request가 수정됐을때 뒤에 인터페이스가 영향받지 않기 위해
        MoneyChargingCommand command = MoneyChargingCommand.builder()
                .targetMembershipId(request.getTargetMembershipId())
                .chargingMoneyAmount(request.getMoneyAmount())
                .build();

        MoneyCharging moneyCharging = moneyUseCase.chargeMoney(command);

        return ResponseEntity.ok(moneyCharging);
    }
}
