package com.cavalieri.educationSystem.repositories;

import com.cavalieri.educationSystem.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String userName);

}


