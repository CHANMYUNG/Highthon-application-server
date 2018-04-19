package com.highthon.highthon3server.dto.invitation;

import com.highthon.highthon3server.domain.invitation.Invitation;
import com.highthon.highthon3server.validator.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class InvitationDto {

    @Email
    private String email;

    public Invitation toEntity() {
        return Invitation.builder().email(email).build();
    }
}
