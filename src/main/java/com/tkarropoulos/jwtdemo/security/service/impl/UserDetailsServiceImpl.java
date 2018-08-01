package com.tkarropoulos.jwtdemo.security.service.impl;

import com.tkarropoulos.jwtdemo.security.domain.ApplicationUser;
import com.tkarropoulos.jwtdemo.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser applicationUser = userRepository.findByUsername(username);

        if(applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }

        return new User(applicationUser.getEmail(), applicationUser.getPassword(), emptyList());
    }
}
