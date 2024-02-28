package com.example.mymsapay.money.adapter.out.service;

import com.example.mymsapay.banking.application.port.out.GetMembershipPort;
import com.example.mymsapay.banking.application.port.out.MembershipStatus;
import com.example.mymsapay.banking.exception.BankAccountException;
import com.example.mymsapay.banking.exception.BankAccountExceptionType;
import com.example.mymsapay.banking.exception.response.ErrorResponse;
import com.example.mymsapay.common.CommonHttpClient;
import com.example.mymsapay.exception.IpcException;
import com.example.mymsapay.exception.IpcExceptionType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.http.HttpResponse;
import java.net.http.HttpTimeoutException;

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
            HttpResponse<String> response = commonHttpClient.sendGetRequest(url);
            String responseBody = response.body();
            ObjectMapper mapper = new ObjectMapper();
            if (response.statusCode() == 200) {
                Membership membership = mapper.readValue(responseBody, Membership.class);
                if (membership.isValid()) {
                    return new MembershipStatus(membership.membershipId(), true);
                } else {
                    return new MembershipStatus(membership.membershipId(), false);
                }
            } else {
                ErrorResponse errorResponse = mapper.readValue(responseBody, ErrorResponse.class);
                if (errorResponse.getCode().equals("M001")) {
                    throw new BankAccountException(BankAccountExceptionType.MEMBERSHIP_NOT_VALID);
                }
                throw new IpcException(IpcExceptionType.MEMBERSHIP_SERVICE_CONNECTION_FAIL);
            }
        } catch (BankAccountException e) {
            throw e;
        } catch (HttpTimeoutException e) {
            log.error("URL: {}", url);
            log.error("2초 타임 아웃");
            throw new IpcException(IpcExceptionType.MEMBERSHIP_SERVICE_CONNECTION_FAIL);
        }
        catch (Exception e) {
            log.error("Exception type = {} ", e.getClass().getName());
            log.error("StackTrace = {} ", (Object) e.getStackTrace());
            log.error("Message = {} ", (Object) e.getMessage());
            throw new IpcException(IpcExceptionType.MEMBERSHIP_SERVICE_CONNECTION_FAIL);
        }
    }
}
