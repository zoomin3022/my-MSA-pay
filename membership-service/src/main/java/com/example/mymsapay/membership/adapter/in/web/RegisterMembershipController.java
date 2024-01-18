package com.example.mymsapay.membership.adapter.in.web;

import com.example.mymsapay.WebAdapter;
import com.example.mymsapay.membership.adapter.in.web.dto.RegisterMembershipRequest;
import com.example.mymsapay.membership.application.port.in.RegisterMembershipCommand;
import com.example.mymsapay.membership.application.port.in.RegisterMembershipUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@WebAdapter
@RequiredArgsConstructor
public class RegisterMembershipController {

    private final RegisterMembershipUseCase registerMembershipUseCase;

    @PostMapping(path = "/membership")
    void registerMembership(@RequestBody RegisterMembershipRequest request) {
        // 먼저 Request가 들어오고

        // request 를 command로 바꿔준다 (중간을 추상화) request가 수정됐을때 뒤에 인터페이스가 영향받지 않기 위해
        RegisterMembershipCommand command = RegisterMembershipCommand.builder()
                .name(request.getName())
                .address(request.getAddress())
                .email(request.getEmail())
                .isValid(true)
                .build();

        registerMembershipUseCase.registerMembership(command);
    }
}
