package com.zakhar.server.service;


import com.zakhar.server.entity.Users;
import com.zakhar.server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public Users getByPrincipal(Principal principal) {
       String username = principal.getName();

       return userRepository.findByUsername(username).orElseThrow(
               () -> new  UsernameNotFoundException(username + " not found")
       );
    }
}
