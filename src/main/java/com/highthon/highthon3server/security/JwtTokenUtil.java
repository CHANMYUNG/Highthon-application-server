package com.highthon.highthon3server.security;

import com.highthon.highthon3server.domain.admin.Admin;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil implements Serializable {

    static final String CLAIM_KEY_USERNAME = "sub";
    static final String CLAIM_KEY_CREATED = "iat";
    private static final long serialVersionUID = -3301605591108950415L;
    private Clock clock = DefaultClock.INSTANCE;
    @Value("${jwt.secret}")
    private String secret;
    private Long expiration = 60 * 60 * 24 * 365L;

    public String getEmailFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject); // subject에 이메일 저장함
    }

    public LocalDateTime getIssuedAtDateFromToken(String token) {
        Date issuedAt = getClaimFromToken(token, Claims::getIssuedAt);
        Instant instant = Instant.ofEpochMilli(issuedAt.getTime());
        return LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername()); // 실제로는 Admin entity의 email
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        final Date createdDate = clock.now();
        final Date expirationDate = calculateExpirationDate(createdDate);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    private Date calculateExpirationDate(Date createdDate) {
        return new Date(createdDate.getTime() + expiration * 1000);
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(clock.now());
    }

    private Boolean isCreatedBeforeLastPasswordReset(LocalDateTime created, LocalDateTime lastPasswordReset) {
        return (lastPasswordReset != null && created.isBefore(lastPasswordReset));
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        Admin admin = (Admin) userDetails;
        final String email = getEmailFromToken(token);
        final LocalDateTime created = getIssuedAtDateFromToken(token);

        return (
                email.equals(admin.getEmail())
                        && !isTokenExpired(token)
                        && !isCreatedBeforeLastPasswordReset(created, admin.getLastPasswordResetDate())
        );
    }

    public Boolean canTokenBeRefreshed(String token, LocalDateTime lastPasswordResetDate) {
        final LocalDateTime created = getIssuedAtDateFromToken(token);
        return !isCreatedBeforeLastPasswordReset(created, lastPasswordResetDate)
                && (!isTokenExpired(token) || ignoreTokenExpiration());
    }

    private boolean ignoreTokenExpiration() {
        return false;
    }
}
