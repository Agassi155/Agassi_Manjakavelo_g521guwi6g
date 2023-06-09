package com.test.security.controller;

import com.test.security.dto.AuthenticationResponse;
import com.test.security.dto.RegisterRequest;
import com.test.security.service.AuthenticationService;
import com.test.security.utils.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        if(request.getEmail() == null || request.getPassword() == null || request.getFirstName() == null){
            throw new CustomException("Missing required fields in the request");
        }
        return ResponseEntity.ok(service.register(request));
    }

}
