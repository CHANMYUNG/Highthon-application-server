package com.highthon.highthon3server.domain.application;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationId;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Sex sex;

    @Column(nullable = false, unique = true)
    private String phone;

    @Column(nullable = false, unique = true)
    private String email;

    @Setter
    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Area area;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Position position;

    @Setter
    @Column(columnDefinition = "BOOLEAN NOT NULL DEFAULT 0")
    private Boolean isAccepted = false;

    @JsonFormat(pattern = "yyyy-MM-dd kk:mm:ss")
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column(nullable = false)
    private String belong;


    @Builder
    public Application(String name, Sex sex, String phone, String email, String password, Area area, Position position, Boolean isAccepted, LocalDateTime createdDate, String belong) {
        this.name = name;
        this.sex = sex;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.area = area;
        this.position = position;
        this.isAccepted = isAccepted;
        this.createdDate = createdDate;
        this.belong = belong;
    }
}
