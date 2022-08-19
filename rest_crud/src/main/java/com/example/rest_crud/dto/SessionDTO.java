package com.example.rest_crud.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SessionDTO implements BaseDto {

    private String accessToken;

    private LocalDateTime expiresAt;

    private String refreshToken;

    private LocalDateTime refreshExpiresAt;


    public SessionDTO(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

}
