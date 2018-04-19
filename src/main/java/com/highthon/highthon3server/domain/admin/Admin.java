package com.highthon.highthon3server.domain.admin;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Admin implements UserDetails {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String adminId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String belong;

    @Column(nullable = false, length = 200)
    private String password;

    @Column(nullable = false, length = 15, unique = true)
    private String phone;

    @CreationTimestamp
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime updatedDate;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "admin_role", joinColumns = @JoinColumn(name = "admin_id")) // adminId가 들어갈 컬럼 이름
    @Column(name = "role", nullable = false) // Role 값이 들어갈 컬럼 이름
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @Column
    private LocalDateTime lastPasswordResetDate;

    @Builder
    public Admin(String name, String email, String belong, String password, String phone) {
        this.name = name;
        this.email = email;
        this.belong = belong;
        this.password = password;
        this.phone = phone;
    }

    public void setRoles(Role... roles) {
        this.roles = new HashSet<>(Arrays.asList(roles));
    }

    public void addRoles(Role... roles) {
        if (this.roles == null) {
            setRoles(roles);
        } else {
            this.roles.addAll(Arrays.asList(roles));
        }

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
