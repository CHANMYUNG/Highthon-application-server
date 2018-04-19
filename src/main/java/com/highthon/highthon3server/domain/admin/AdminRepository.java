package com.highthon.highthon3server.domain.admin;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface AdminRepository extends CrudRepository<Admin, String> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Admin a " +
            "WHERE a.email = :email")
    void deleteByEmail(@Param("email") String email);

    Optional<Admin> findByEmail(String email);

    Boolean existsByEmail(String email);

}
