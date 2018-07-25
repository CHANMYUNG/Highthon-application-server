package com.highthon.highthon3server.domain.application;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(nullable = false, unique = true)
    private String email;

    @Setter
    @Column(nullable = false)
    @JsonIgnore
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Sex sex;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Area area;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Position position;

    @Column(nullable = false, unique = true)
    private String phone;

    @Setter
    @Column(nullable = false)
    private String belong;

    @Setter
    @Column(columnDefinition = "BOOLEAN NOT NULL DEFAULT 0")
    private Boolean isAccepted = false;

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd kk:mm:ss")
    private LocalDateTime acceptedDate;

    @JsonFormat(pattern = "yyyy-MM-dd kk:mm:ss")
    private LocalDateTime createdDate = LocalDateTime.now();


    @Builder
    public Application(String name, String email, String password, Sex sex, Area area, Position position, String phone, String belong, Boolean isAccepted) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.sex = sex;
        this.area = area;
        this.position = position;
        this.phone = phone;
        this.belong = belong;
        this.isAccepted = isAccepted;
    }

    @Override
    public String toString() {
        return "Application{" +
                "applicationId=" + applicationId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", sex=" + sex +
                ", area=" + area +
                ", position=" + position +
                ", phone='" + phone + '\'' +
                ", belong='" + belong + '\'' +
                ", isAccepted=" + isAccepted +
                ", acceptedDate=" + acceptedDate +
                ", createdDate=" + createdDate +
                '}';
    }
}
