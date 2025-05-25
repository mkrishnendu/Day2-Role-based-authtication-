package com.example.BookStore.controller;


import com.example.BookStore.DTO.RegisterRequestDto;
import com.example.BookStore.model.User;
import com.example.BookStore.service.AuthticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AuthticationService authticationService;
    @PostMapping("/registerAdminUser")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> registerAdminUser(@RequestBody RegisterRequestDto registerRequestDto)
    {
        return ResponseEntity.ok(authticationService.registerAdminUser(registerRequestDto));

    }
}
