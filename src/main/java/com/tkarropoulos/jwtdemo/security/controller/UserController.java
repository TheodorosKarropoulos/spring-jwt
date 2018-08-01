package com.tkarropoulos.jwtdemo.security.controller;

import com.tkarropoulos.jwtdemo.security.domain.ApplicationUser;
import com.tkarropoulos.jwtdemo.security.repository.UserRepository;
import com.tkarropoulos.jwtdemo.utils.CustomLoggerMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserController(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@Valid @RequestBody ApplicationUser user) {
        // Check if user already exists
        ApplicationUser checkUser = userRepository.findByUsername(user.getUsername());
        if(checkUser != null){
            log.warn("A user with this username already exists");
            return new ResponseEntity<>(new CustomLoggerMessages("A user with this username already exists"), HttpStatus.CONFLICT);
        }
        checkUser = userRepository.findByEmail(user.getEmail());
        if(checkUser != null) {
            log.warn("A user with this email already exists");
            return new ResponseEntity<>(new CustomLoggerMessages("A user with this email already exists"), HttpStatus.CONFLICT);
        }

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        log.info("A new user signed up");

        return new ResponseEntity<>(new CustomLoggerMessages("A new user signed up"), HttpStatus.OK);
    }
}
