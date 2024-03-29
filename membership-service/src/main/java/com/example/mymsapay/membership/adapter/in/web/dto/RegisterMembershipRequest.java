package com.example.mymsapay.membership.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterMembershipRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String address;
    @NotBlank
    private String email;
}
