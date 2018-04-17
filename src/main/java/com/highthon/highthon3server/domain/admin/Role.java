package com.highthon.highthon3server.domain.admin;

import org.springframework.security.core.GrantedAuthority;

public enum Role {
    BASIC,  // 일반적인 운영진 권한
    SUPER;  // 슈퍼 계정 (운영진 추가/삭제 권한 가짐)
}
