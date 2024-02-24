package com.example.mymsapay.membership.adapter.in.web;

import com.example.mymsapay.common.WebAdapter;
import com.example.mymsapay.membership.adapter.in.web.dto.ModifyMembershipRequest;
import com.example.mymsapay.membership.application.port.in.ModifyMembershipCommand;
import com.example.mymsapay.membership.application.port.in.ModifyMembershipUseCase;
import com.example.mymsapay.membership.domain.Membership;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class ModifyMembershipController {
    private final ModifyMembershipUseCase modifyMembershipUseCase;

    @PatchMapping("/membership/{membershipId}")
    ResponseEntity<Membership> modifyMembershipByMemberId(@PathVariable Long membershipId, @RequestBody ModifyMembershipRequest request) {
        ModifyMembershipCommand modifyMembershipCommand =
                ModifyMembershipCommand.builder()
                        .name(request.getName())
                        .address(request.getAddress())
                        .email(request.getEmail())
                        .isValid(true)
                        .build();

        return ResponseEntity.ok(modifyMembershipUseCase.modifyMembership(membershipId, modifyMembershipCommand));
    }
}
