package com.example.BookStore.controller;
import com.example.BookStore.DTO.LoginRequestDto;
import com.example.BookStore.DTO.LoginResponseDto;
import com.example.BookStore.DTO.RegisterRequestDto;
import com.example.BookStore.model.User;
import com.example.BookStore.service.AuthticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthticationService authticationService;


    @PostMapping("/registerNormaluser")
    public ResponseEntity<User> resgisterNormalUser(@RequestBody RegisterRequestDto registerRequestDto){
       return ResponseEntity.ok(authticationService.registerNormalUser(registerRequestDto));
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto>login(@RequestBody LoginRequestDto loginRequestDto){
        return ResponseEntity.ok(authticationService.login(loginRequestDto));
    }


}
