package com.example.Java_Server_Part.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenAndUserIdDto {
    private String token;
    private Long user_id;
    private String username;
}
