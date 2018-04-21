package com.highthon.highthon3server.controller;

import com.highthon.highthon3server.domain.admin.Admin;
import com.highthon.highthon3server.dto.auth.AdminLoginDto;
import com.highthon.highthon3server.dto.auth.AdminSignupDto;
import com.highthon.highthon3server.exception.AuthenticationException;
import com.highthon.highthon3server.exception.InvitationCodeNotFoundException;
import com.highthon.highthon3server.exceptionHandler.ErrorResponse;
import com.highthon.highthon3server.security.JwtTokenUtil;
import com.highthon.highthon3server.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AuthenticationFailureExpiredEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Objects;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AdminService adminService;


    @PostMapping("/admin/login")
    @ResponseBody
    public ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody AdminLoginDto loginRequest) {

        final UserDetails userDetails = adminService.loadUserByUsername(loginRequest.getEmail());
        authenticate(userDetails.getUsername(), loginRequest.getPassword());
        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new Token(token));
    }

    @PostMapping("/signup")
    @ResponseBody
    public ResponseEntity<?> create(@Valid @RequestBody AdminSignupDto dto) {
        //TODO: create service layer class, write some signup logic, and call that at here
        final String password = dto.getPassword(); // BCryptEncoder로 encoding되기 전의 비밀번호
        Admin admin = adminService.createAdmin(dto);
        System.out.println(admin.getEmail());
        System.out.println(password);
        authenticate(admin.getUsername(), password); // BCryptEncoder로 encoding되기 전의 비밀번호
        final String token = jwtTokenUtil.generateToken(admin);

        return ResponseEntity.ok(new Token(token));
    }


    @GetMapping("/admin")
    public String admin() {
        return "hello, admin!";
    }

    @GetMapping("/super")
    public String _super() {
        return "hello, super!";
    }

    private void authenticate(String username, String password) {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new AuthenticationException("User is disabled!", e);
        } catch (BadCredentialsException e) {
            throw new AuthenticationException("Bad credentials!", e);
        }
    }

    @ExceptionHandler({InvitationCodeNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse invitationCodeNotFoundExceptionHandler(HttpServletRequest request, Exception e) {
        return new ErrorResponse(request.getRequestURI(), e.getMessage());
    }

    private class Token {
        private String accessToken;

        public String getAccessToken() {
            return accessToken;
        }

        Token(String accessToken) {
            this.accessToken = accessToken;
        }
    }
}
