package com.highthon.highthon3server.domain.application;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

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

    @Query("SELECT new com.highthon.highthon3server.domain.application.ApplicationCondition(a.name, a.isAccepted, " +
            "(CASE " +
            "   WHEN (a.isAccepted = FALSE) " +
            "       THEN (SELECT count(sub_a) FROM Application sub_a WHERE sub_a.isAccepted=FALSE AND sub_a.area = a.area AND sub_a.position = a.position AND sub_a.createdDate <= a.createdDate) " +
            "   ELSE NULL " +
            "END)) " +
            "FROM Application a " +
            "WHERE a.applicationId = :applicationId")
    ApplicationCondition getApplicationConditionById(@Param("applicationId") Long applicationId);

    Optional<Application> findByEmail(String email);
}
