package com.example.demo.controllers;


import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.example.demo.auth.*;
import com.example.demo.entities.Club;
import com.example.demo.entities.ERole;
import com.example.demo.entities.Role;
import com.example.demo.entities.UserBD;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserBDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserBDRepository userRepository;


    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @GetMapping(path = "/users")
    public List<UserBD> getUsers(){
        return userRepository.findAll();
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        //boolean isPasswordMatch = encoder.matches(login, encodedPassword);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        boolean isEnabled = userRepository.findByuserName(loginRequest.getUsername()).get().isEnabled();
        boolean nonLocked = userRepository.findByuserName(loginRequest.getUsername()).get().isAccountNonLocked();
        String email = userRepository.findByuserName(loginRequest.getUsername()).get().getEmail();
        String password = userRepository.findByuserName(loginRequest.getUsername()).get().getPassword();
        if(!userRepository.findByuserName(loginRequest.getUsername()).get().isEnabled())
            return null;
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                roles,
                password,
                email,
                nonLocked,
                isEnabled));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser( @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByuserName(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }
        System.out.println("Request username : " + signUpRequest.getUsername());
        System.out.println("Request password: " + signUpRequest.getPassword());
        System.out.println("Request role: " + signUpRequest.getUserRole());
        System.out.println("Request email: " + signUpRequest.getEmail());

        Set<Role> roles = new HashSet<>();

        //ROLE_USER_INTERNAL,
        //    ROLE_USER_EXTERNAL,
        //    ROLE_ADMIN


        switch (signUpRequest.getUserRole()) {
            case "Etudiant exterieur": {
                Role userRole = roleRepository.findByName(ERole.ROLE_USER_EXTERNAL)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(userRole);
                break;
            }
            case "Etudiant UIR": {
                Role userRole = roleRepository.findByName(ERole.ROLE_USER_INTERNAL)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(userRole);
                break;
            }
            case "Administrateur": {
                Role userRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(userRole);
                break;
            }
            default: {
                Role userRole = roleRepository.findByName(ERole.ROLE_USER_EXTERNAL)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(userRole);
            }
        }


        // Create new user's account
        UserBD user = new UserBD(signUpRequest.getUsername(),
                roles,
                signUpRequest.getPassword(),
                signUpRequest.getEmail());
        if(roles.contains(roleRepository.findByName(ERole.ROLE_ADMIN).orElseThrow(() -> new RuntimeException("Error: Role is not found."))))
            user.setEnabled(false);
        else
            user.setEnabled(true);

        if(roles.contains(roleRepository.findByName(ERole.ROLE_USER_INTERNAL).orElseThrow(() -> new RuntimeException("Error: Role is not found."))))
            user.setAccountNonLocked(false);
        else
            user.setAccountNonLocked(true);

        userRepository.save(user);

        System.out.println(userRepository.save(user));

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

}
