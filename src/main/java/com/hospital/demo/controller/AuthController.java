package com.hospital.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.hospital.demo.config.JwtUtils;
import com.hospital.demo.service.CustomUserDetailsService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        // authenticationManager.authenticate(
        //     new UsernamePasswordAuthenticationToken(
        //         loginRequest.getUsername(),
        //         loginRequest.getPassword()
        //     )
        // );

        // UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
        // String token = jwtUtils.generateToken(userDetails);
        // return ResponseEntity.ok(token);

        // Authenticate using the manager
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
            )
        );

        // Generate JWT token
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(token);
    }
}
