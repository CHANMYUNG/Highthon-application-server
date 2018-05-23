package com.highthon.highthon3server.controller;


import com.highthon.highthon3server.exception.SelfHandleException;
import com.highthon.highthon3server.security.JwtTokenUtil;
import com.highthon.highthon3server.service.authority.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class AuthorityController {

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @PostMapping("/admin/{adminId}/authority/super")
    public void delegateSuperAuthority(@PathVariable("adminId") String adminId, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader).substring(7);
        String myId = jwtTokenUtil.getAdminIdFromToken(token);
        if (myId.equals(adminId)) throw new SelfHandleException();
        authorityService.grantSuperAuthorityToOtherAdminAndRefuseMine(myId, adminId);
    }
}
