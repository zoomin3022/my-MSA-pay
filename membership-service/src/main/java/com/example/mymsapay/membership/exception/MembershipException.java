package com.example.mymsapay.membership.exception;

import com.example.mymsapay.exception.CustomException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MembershipException extends CustomException {

    private final MembershipExceptionType membershipExceptionType;

    @Override
    public MembershipExceptionType getCustomExceptionType() {
        return membershipExceptionType;
    }
}
