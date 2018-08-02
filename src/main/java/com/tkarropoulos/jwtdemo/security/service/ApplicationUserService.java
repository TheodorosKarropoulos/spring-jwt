package com.tkarropoulos.jwtdemo.security.service;

import com.tkarropoulos.jwtdemo.security.domain.ApplicationUser;


public interface ApplicationUserService {

    void createPasswordResetTokenForUser(ApplicationUser user, String token);
}
