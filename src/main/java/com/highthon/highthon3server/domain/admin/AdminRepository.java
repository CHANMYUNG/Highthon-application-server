package com.highthon.highthon3server.domain.admin;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdminRepository extends CrudRepository<Admin, String> {

    @Query("DELETE FROM Admin a " +
            "WHERE a.email = :email")
    void deleteByEmail(@Param("email") String email);

    @Query("SELECT a FROM Admin a " +
            "WHERE a.email = :email")
    Admin findByEmail(@Param("email") String email);

    @Query("SELECT ")
    List<String> getRolesByEmail(@Param("admin_id") String adminId);
}
