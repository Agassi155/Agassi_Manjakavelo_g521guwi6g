package com.test.security.service;

import com.test.security.dto.AuthenticationResponse;
import com.test.security.dto.RegisterRequest;
import com.test.security.model.Role;
import com.test.security.model.User;
import com.test.security.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class AuthenticationService implements UserDetailsService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    /**
     * Registers a new user.
     */
    public AuthenticationResponse register(RegisterRequest request) {
        User user = User.builder().username(request.getFirstName())
                .email(request.getEmail())
                .password(passwordEncoder.encode( request.getPassword()))
                .role(Role.USER)
                .build();
        repository.save(user);
        var jwtToken  = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    /**
     * Loads a user by username.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = repository.findByEmail(username);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                Collections.emptyList()
        );
    }



}
