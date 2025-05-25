package com.example.BookStore.service;


import com.example.BookStore.repo.UserRecordRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class CustomUserDetailService implements UserDetailsService {

    private UserRecordRepository userRecordRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRecordRepository.findByUsername(username).orElseThrow(()->new RuntimeException("user not found"));
    }
}
