package com.example.mymsapay.membership.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
// 외부에서 생성자로 생성을 막기 위함
// Entity 의 경우 프록시 생성을 위해 private 은 불가 protected 로 선언했지만 여기서는 private 가능
public class Membership {
    @Getter
    private final String membershipId;
    @Getter
    private final String name;
    @Getter
    private final String email;
    @Getter
    private final String address;
    @Getter
    private final boolean isValid;
    @Getter
    private final String aggregateIdentifier;

    public static Membership generateMember(
            MembershipId membershipId, MembershipName membershipName, MembershipEmail membershipEmail, MembershipAddress membershipAddress, MembershipIsValid membershipIsValid,
            MembershipAggregateIdentifier membershipAggregateIdentifier) {
        return new Membership(
                membershipId.membershipId,
                membershipName.nameValue,
                membershipEmail.emailValue,
                membershipAddress.addressValue,
                membershipIsValid.isValidValue,
                membershipAggregateIdentifier.aggregateIdentifier
        );
    }

    /**
     * 그동안 빌더 패턴이나 정적 팩토리 메소드 패턴으로 생성할때 그냥 파라미터로 받아왔다
     * 아래같이 내부 클래스를 사용하면 데이터 유효성 검증, 의미 파악에 더 도움이 되긴 할듯
     */
    @Value
    public static class MembershipId {
        public MembershipId(String value) {
            this.membershipId = value;
        }

        String membershipId;
    }

    @Value
    public static class MembershipName {
        public MembershipName(String value) {
            this.nameValue = value;
        }

        String nameValue;
    }

    @Value
    public static class MembershipEmail {
        public MembershipEmail(String value) {
            this.emailValue = value;
        }

        String emailValue;
    }

    @Value
    public static class MembershipAddress {
        public MembershipAddress(String value) {
            this.addressValue = value;
        }

        String addressValue;
    }

    @Value
    public static class MembershipIsValid {
        public MembershipIsValid(boolean value) {
            this.isValidValue = value;
        }

        boolean isValidValue;
    }

    @Value
    public static class MembershipAggregateIdentifier {
        public MembershipAggregateIdentifier(String value) {
            this.aggregateIdentifier = value;
        }

        String aggregateIdentifier;
    }
}
