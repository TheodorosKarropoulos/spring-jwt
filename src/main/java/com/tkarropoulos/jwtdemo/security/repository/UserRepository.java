package com.tkarropoulos.jwtdemo.security.repository;

import com.tkarropoulos.jwtdemo.security.domain.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<ApplicationUser, Long> {

    ApplicationUser findByUsername(String username);

    ApplicationUser findByEmail(String email);

}

