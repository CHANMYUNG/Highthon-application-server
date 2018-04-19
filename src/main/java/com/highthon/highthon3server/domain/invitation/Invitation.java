package com.highthon.highthon3server.domain.invitation;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class Invitation {

    @Id
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String invitationCode;

    @Builder
    public Invitation(String email) {
        this.email = email;
        this.invitationCode = UUID.randomUUID().toString().replace("-", "");
    }
}
