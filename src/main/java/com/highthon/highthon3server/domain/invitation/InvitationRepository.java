package com.highthon.highthon3server.domain.invitation;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface InvitationRepository extends CrudRepository<Invitation, String> {
    Optional<Invitation> findByInvitationCode(String invitationCode);
}
