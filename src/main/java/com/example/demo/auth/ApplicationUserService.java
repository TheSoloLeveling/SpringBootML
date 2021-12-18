package com.example.demo.auth;


import com.example.demo.entities.UserBD;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApplicationUserService implements UserDetailsService {


    private final PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    public ApplicationUserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserBD> user = userRepository.findByUserName(username);
        user.orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found", username)));

        return user.map(user1 -> new ApplicationUser(passwordEncoder, user1)).get();


    }
}
