package com.highthon.highthon3server.domain.admin;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRoleRepository extends CrudRepository<AdminRole, Long> {
}
