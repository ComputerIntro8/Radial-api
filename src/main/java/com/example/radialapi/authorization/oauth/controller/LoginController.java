package com.example.radialapi.authorization.oauth.controller;


import com.example.radialapi.authorization.jwt.dto.AuthTokens;
import com.example.radialapi.authorization.oauth.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping(value ="/login/oauth2", produces="application/json")
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/code/{registrationId}")
    public ResponseEntity<Object> googleLogin(@PathVariable String registrationId, @RequestParam String code) {
        try {
            AuthTokens tokens = loginService.socialLogin(registrationId, code);
            long userId = tokens.getUserId();  // 밑에 리디렉션 uri 바꿔야함
            String redirectUrl = "https://your-client-app.com/login-success?userId=" + userId;
            // 리디렉션 응답 생성
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(new URI(redirectUrl));
            return new ResponseEntity<>(headers, HttpStatus.FOUND);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Login failed due to server error");
        }
    }

}
