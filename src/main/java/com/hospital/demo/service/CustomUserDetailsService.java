package com.hospital.demo.service;

import com.hospital.demo.entity.User;
import com.hospital.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // @Override
    // public UserDetails loadUserByUsername(String username) {
    // User user = userRepository.findByUsername(username)
    // .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    // return new org.springframework.security.core.userdetails.User(
    // user.getUsername(),
    // user.getPassword(),
    // AuthorityUtils.createAuthorityList("ROLE_" + user.getRole().getName())
    // );
    // }
    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName()))
                        .toList());
    }
    // @Override
    // public UserDetails loadUserByUsername(String username) throws
    // UsernameNotFoundException {
    // User user = userRepository.findByUsername(username)
    // .orElseThrow(() -> new UsernameNotFoundException("User not found: " +
    // username));

    // return org.springframework.security.core.userdetails.User.builder()
    // .username(user.getUsername())
    // .password(user.getPassword())
    // .roles(user.getRoles().stream()
    // .map(role -> role.getName().replace("ROLE_", "")) // Convert "ROLE_NURSE" to
    // "NURSE"
    // .toArray(String[]::new))
    // .build();
    // }
}