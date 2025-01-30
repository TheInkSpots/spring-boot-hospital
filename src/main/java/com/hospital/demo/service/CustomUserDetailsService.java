package com.hospital.demo.service;

import com.hospital.demo.entity.User;
import com.hospital.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) {
    User user = userRepository.findByUsername(username)
      .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    return new org.springframework.security.core.userdetails.User(
      user.getUsername(),
      user.getPassword(),
      AuthorityUtils.createAuthorityList("ROLE_" + user.getRole().getName())
    );
  }
}