package com.example.BookStore.DTO;


import lombok.Data;

@Data
public class RegisterRequestDto {
    private String username;
    private String password;
    private String Email;
}
