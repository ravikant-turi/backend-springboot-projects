package com.java.security.service;

import com.java.security.model.CustomUserDetails;
import com.java.security.model.User;
import com.java.security.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user= this.userRepository.findByUsername(
                username).orElseThrow(
                        ()->new UsernameNotFoundException
                                ("UserNot found"));

        return new CustomUserDetails(user);
    }
}
