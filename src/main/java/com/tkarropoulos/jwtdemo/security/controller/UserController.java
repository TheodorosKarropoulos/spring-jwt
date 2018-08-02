package com.tkarropoulos.jwtdemo.security.controller;

import com.tkarropoulos.jwtdemo.security.domain.ApplicationUser;
import com.tkarropoulos.jwtdemo.security.repository.UserRepository;
import com.tkarropoulos.jwtdemo.security.service.ApplicationUserService;
import com.tkarropoulos.jwtdemo.utils.CustomLoggerMessages;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@Api(description = "User controller", tags = "User")
public class UserController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ApplicationUserService applicationUserService;

    @Autowired
    public UserController(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @ApiOperation("User sign up")
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

    @ApiOperation("Reset user's password")
    @PostMapping("/reset-password")
    public ResponseEntity resetPassword(@RequestParam("email") String email, HttpServletRequest request) {
        ApplicationUser user = userRepository.findByEmail(email);
        if(user == null){
            log.warn("User not found with given email {}", email);
            return new ResponseEntity(new CustomLoggerMessages("User not found"), HttpStatus.NOT_FOUND);
        }

        String token = UUID.randomUUID().toString();
        log.info("A reset password token generated");
        applicationUserService.createPasswordResetTokenForUser(user, token);
        return new ResponseEntity(new CustomLoggerMessages("An email was send for password reset"), HttpStatus.OK);
    }
}
