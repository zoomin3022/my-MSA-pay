package com.example.mymsapay.membership.adapter.in.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ModifyMembershipRequest {
    private String name;
    private String address;
    private String email;
}

