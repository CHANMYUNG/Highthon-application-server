package com.highthon.highthon3server.domain.application;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

    Integer countByAreaAndPosition(Area area, Position position);

    @Query("SELECT count(a) " +
            "FROM Application a " +
            "WHERE a.createdDate <= :#{#application.createdDate} AND a.isAccepted = FALSE " +
            "AND a.area = :#{#application.area} " +
            "AND a.position = :#{#application.position}")
    Integer getWaitingNumber(@Param("application") Application application);

    Boolean existsByPhone(@NotNull String pheon);

    Boolean existsByEmail(@NotNull String email);

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

    Optional<Application> findFirstByAreaAndPositionAndIsAcceptedIsFalseOrderByCreatedDate(Area area, Position position);

}
