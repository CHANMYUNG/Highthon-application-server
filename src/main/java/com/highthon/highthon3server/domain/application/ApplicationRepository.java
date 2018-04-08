package com.highthon.highthon3server.domain.application;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

    @Query("SELECT count(a) " +
            "FROM Application a " +
            "WHERE a.area = :area AND a.position = :position ")
    Integer countByAreaAndPosition(@Param("area") Area area, @Param("position") Position position);

    @Query("SELECT count(a) " +
            "FROM Application a " +
            "WHERE a.createdDate <= :createdDate AND a.isAccepted = FALSE ")
    Integer getWaitingNumber(@Param("createdDate") LocalDateTime createdDate);

    @Query("SELECT count(a) " +
            "FROM Application a " +
            "WHERE a.phone = :phone")
    Integer countByPhone(@Param("phone") String phone);

    @Query("SELECT count(a) " +
            "FROM Application a " +
            "WHERE a.email = :email")
    Integer countByEmail(@Param("email") String email);
}
