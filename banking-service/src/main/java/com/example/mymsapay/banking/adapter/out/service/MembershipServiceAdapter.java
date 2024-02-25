package com.example.mymsapay.banking.adapter.out.service;

import com.example.mymsapay.banking.application.port.out.GetMembershipPort;
import com.example.mymsapay.banking.application.port.out.MembershipStatus;
import com.example.mymsapay.banking.exception.BankAccountException;
import com.example.mymsapay.banking.exception.BankAccountExceptionType;
import com.example.mymsapay.common.CommonHttpClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MembershipServiceAdapter implements GetMembershipPort {

    private final CommonHttpClient commonHttpClient;

    private final String membershipServiceUrl;

    public MembershipServiceAdapter(CommonHttpClient commonHttpClient,
                                     @Value("${service.membership.url}") String membershipServiceUrl) {
        this.commonHttpClient = commonHttpClient;
        this.membershipServiceUrl = membershipServiceUrl;
    }

    @Override
    public MembershipStatus getMembership(Long membershipId) {

        String url = String.join("/", membershipServiceUrl, "membership", membershipId.toString());
        try {
            String jsonResponse = commonHttpClient.sendGetRequest(url).body();
            // json Membership

            ObjectMapper mapper = new ObjectMapper();
            Membership membership = mapper.readValue(jsonResponse, Membership.class);

            if (membership.isValid()){
                return new MembershipStatus(membership.membershipId(), true);
            } else {
                return new MembershipStatus(membership.membershipId(), false);
            }
        } catch (Exception e) {
            log.error("StackTrace = {} ", (Object) e.getStackTrace());
            log.error("Message = {} ", (Object) e.getMessage());
            throw new BankAccountException(BankAccountExceptionType.MEMBERSHIP_SERVICE_CONNECTION_FAIL);
        }
    }
}
