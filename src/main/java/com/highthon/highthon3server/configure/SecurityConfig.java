package com.highthon.highthon3server.configure;

import com.highthon.highthon3server.domain.admin.Role;
import com.highthon.highthon3server.security.JwtAuthenticationEntryPoint;
import com.highthon.highthon3server.security.JwtAuthorizationTokenFilter;
import com.highthon.highthon3server.security.JwtTokenUtil;
import com.highthon.highthon3server.security.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Value("${jwt.authenticationPath}")
    private String authenticationPath;

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jwtUserDetailsService)
                .passwordEncoder(passwordEncoderBean());
    }

    @Bean
    public PasswordEncoder passwordEncoderBean() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/api/admin").hasAuthority(Role.BASIC.name())
                .antMatchers("/api/super").hasAuthority(Role.SUPER.name())
                .antMatchers("/api/applications/**").hasAuthority(Role.BASIC.name())
                .antMatchers(HttpMethod.POST, "/api/admin/{adminId}/authority/super").hasAuthority(Role.SUPER.name())
                .antMatchers(HttpMethod.POST, "/api/invitation").hasAuthority(Role.SUPER.name())
                .antMatchers(HttpMethod.POST, "/api/upload").hasAuthority(Role.BASIC.name())
                .antMatchers(HttpMethod.DELETE, "/api/admin/{adminId}").hasAuthority(Role.SUPER.name())
                .antMatchers(HttpMethod.POST, "/api/mail").hasAuthority(Role.BASIC.name())
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .logout();

        // Custom JWT based security filter
        JwtAuthorizationTokenFilter authenticationTokenFilter = new JwtAuthorizationTokenFilter(jwtUserDetailsService, jwtTokenUtil, tokenHeader);

        http
                .addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        http
                .headers()
                .frameOptions().sameOrigin()
                .cacheControl();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers(
                        HttpMethod.POST,
                        authenticationPath
                )
                .and()
                .ignoring()
                .regexMatchers(HttpMethod.GET,
                        "^(?:[^a]+|a(?:$|[^p]|p(?:$|[^i])))*$")
                .antMatchers(
                        HttpMethod.GET,
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js"
                );
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
