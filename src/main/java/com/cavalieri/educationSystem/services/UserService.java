package com.cavalieri.educationSystem.services;

import com.cavalieri.educationSystem.DTO.UserDTO;
import com.cavalieri.educationSystem.entities.User;
import com.cavalieri.educationSystem.repositories.UserRepository;
import com.cavalieri.educationSystem.services.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService implements UserDetailsService {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Transactional(readOnly = true)
    public UserDTO findById(Long id){

        authService.validateSelfOrAdmin(id);

        User entity = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return new UserDTO(entity);

    }

    public  UserDTO findUserAuthenticated(){

        User user = authService.Authenticated();
        User entity = userRepository.findByEmail(user.getUsername());
        UserDTO dto = new UserDTO(entity);
        return dto;

    }


    //-----------------------------------------------------------------------------------
    //authentication zone:

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username);
        if (user == null){
            logger.error("user not found " + username);
            throw new UsernameNotFoundException("email not found");
        }
        logger.info("user found" + username);
        return user;
    }

}