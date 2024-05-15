package com.example.radialapi.authorization.jwt.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthTokens {
    private String accessToken;
    private String refreshToken;
    private String grantType;
    private Long expiresIn;
    private Long userId;

    // 정적팩토리 메서드 패턴
    public static AuthTokens of(String accessToken, String refreshToken, String grantType, Long expiresIn, Long userId) {
        return new AuthTokens(accessToken, refreshToken, grantType, expiresIn, userId);
    }
}
