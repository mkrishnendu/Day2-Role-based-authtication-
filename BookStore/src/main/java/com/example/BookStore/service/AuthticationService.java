package com.example.BookStore.service;

import com.example.BookStore.DTO.LoginRequestDto;
import com.example.BookStore.DTO.LoginResponseDto;
import com.example.BookStore.DTO.RegisterRequestDto;
import com.example.BookStore.JWT.JwtService;
import com.example.BookStore.model.User;
import com.example.BookStore.repo.UserRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthticationService {

    @Autowired
    private UserRecordRepository userRecordRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getUsername(),
                        loginRequestDto.getPassword())
        );

        User user = userRecordRepository.findByUsername(loginRequestDto.getUsername())
                .orElseThrow(() -> new RuntimeException("User not Found"));

        String token = jwtService.generateToken(user);

        return LoginResponseDto.builder()
                .token(token)
                .username(user.getUsername())
                .roles(user.getRoles())
                .build();
    }

    public User registerAdminUser(RegisterRequestDto registerRequestDto) {
        if (userRecordRepository.findByUsername(registerRequestDto.getUsername()).isPresent()) {
            throw new RuntimeException("User already registered");
        }

        Set<String> roles = new HashSet<>();
        roles.add("ROLE_USER");
        roles.add("ROLE_ADMIN");

        User user = new User();
        user.setUsername(registerRequestDto.getUsername());
        user.setEmail(registerRequestDto.getEmail());
        user.setPassword(encoder.encode(registerRequestDto.getPassword())); // ✅ encode password
        user.setRoles(roles);

        return userRecordRepository.save(user);
    }

    public User registerNormalUser(RegisterRequestDto registerRequestDto) {
        if (userRecordRepository.findByUsername(registerRequestDto.getUsername()).isPresent()) {
            throw new RuntimeException("User already registered");
        }

        Set<String> roles = new HashSet<>();
        roles.add("ROLE_USER");

        User user = new User();
        user.setUsername(registerRequestDto.getUsername());
        user.setEmail(registerRequestDto.getEmail());
        user.setPassword(encoder.encode(registerRequestDto.getPassword())); // ✅ encode password
        user.setRoles(roles);

        return userRecordRepository.save(user);
    }
}
