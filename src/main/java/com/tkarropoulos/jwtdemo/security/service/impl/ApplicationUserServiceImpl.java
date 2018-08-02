package com.tkarropoulos.jwtdemo.security.service.impl;

import com.tkarropoulos.jwtdemo.security.domain.ApplicationUser;
import com.tkarropoulos.jwtdemo.security.domain.PasswordResetToken;
import com.tkarropoulos.jwtdemo.security.repository.PasswordResetTokenRepository;
import com.tkarropoulos.jwtdemo.security.service.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserServiceImpl implements ApplicationUserService {

    @Autowired
    private PasswordResetTokenRepository resetTokenRepository;

    @Override
    public void createPasswordResetTokenForUser(ApplicationUser user, String token) {
        PasswordResetToken resetToken = new PasswordResetToken(user, token);
        resetTokenRepository.save(resetToken);
    }
}
