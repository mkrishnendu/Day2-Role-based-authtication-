package com.example.BookStore.DTO;


import lombok.Builder;
import lombok.Data;

import java.util.Set;
@Data
@Builder
public class LoginResponseDto {
private String username;
private String token;
private Set<String> roles;
}
