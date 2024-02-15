package com.example.mymsapay.banking.adapter.out.persistence.firmbanking;

import com.example.mymsapay.banking.domain.RequestFirmbanking;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "request_firmbanking")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestFirmbankingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requestFirmbankingId;

    private String fromBankName;
    private String fromBankAccountNumber;
    private String toBankName;
    private String toBankAccountNumber;
    /*
     사실 이 부분은 회원간의 송금일때와 일반 계좌로 송금일때를 Entity로 나누고 싶었지만, 이번에는 간단하게 하기 위해 하나로 통합했습니다.
     회원간의 송금일때는 fromMembershipId, toMembershipId나 fromRegisteredBankAccountId, toRegisteredBankAccountId를 사용하고,
     many to many 로 연결하는 등의 방법이 있을거고
     일반 계좌로 송금일때는 fromBankName, fromBankAccountNumber, toBankName, toBankAccountNumber를 사용하면 될거라 생각합니다.
     */
    private int moneyAmount;
    @Setter
    private RequestFirmbanking.FirmbankingRequestStatus firmbankingRequestStatus; //요청 완료 실패 여부 나중에 Enum으로 변경

    @Builder

    public RequestFirmbankingEntity(String fromBankName, String fromBankAccountNumber, String toBankName, String toBankAccountNumber, int moneyAmount, RequestFirmbanking.FirmbankingRequestStatus firmbankingRequestStatus) {
        this.fromBankName = fromBankName;
        this.fromBankAccountNumber = fromBankAccountNumber;
        this.toBankName = toBankName;
        this.toBankAccountNumber = toBankAccountNumber;
        this.moneyAmount = moneyAmount;
        this.firmbankingRequestStatus = firmbankingRequestStatus;
    }
}
