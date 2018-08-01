// This class is responsible for the authentication process

package com.tkarropoulos.jwtdemo.security.config;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tkarropoulos.jwtdemo.security.domain.ApplicationUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.tkarropoulos.jwtdemo.security.config.SecurityConstantsConfig.EXPIRATION_TIME;
import static com.tkarropoulos.jwtdemo.security.config.SecurityConstantsConfig.HEADER_STRING;
import static com.tkarropoulos.jwtdemo.security.config.SecurityConstantsConfig.TOKEN_PREFIX;
import static com.tkarropoulos.jwtdemo.security.config.SecurityConstantsConfig.SECRET;

public class JWTAuthenticationFilterConfig extends UsernamePasswordAuthenticationFilter{

    private AuthenticationManager authenticationManager;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public JWTAuthenticationFilterConfig(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            ApplicationUser credentials = new ObjectMapper()
                    .readValue(request.getInputStream(), ApplicationUser.class);

            log.warn("Authentication error, username or password not valid");

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            credentials.getUsername(),
                            credentials.getPassword(),
                            new ArrayList<>()
                    )
            );
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // On successful login generate a JWT for this user
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String token = JWT.create()
                .withSubject(((User) authResult.getPrincipal()).getPassword())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(SECRET.getBytes()));
        response.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
        log.info("Successful authentication");
    }
}
