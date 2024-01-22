package com.example.mymsapay.membership.application.port.in;


import com.example.mymsapay.SelfValidating;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public
class ModifyMembershipCommand extends SelfValidating<ModifyMembershipCommand> {

    private final String name;

    private final String email;

    private final String address;

    private final boolean isValid;

    public ModifyMembershipCommand(String name, String email, String address, boolean isValid) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.isValid = isValid;

        this.validateSelf();
    }
}
