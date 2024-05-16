package com.example.radialapi.authorization.oauth.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
@RequestMapping("/api")
public class OAuthController {

    @Autowired
    private Environment env;

    @GetMapping("/oauth2/authorization/google")
    public void redirectToGoogle(HttpServletResponse response) throws IOException {
        String clientId = env.getProperty("oauth2.google.client-id");
        String redirectUri = env.getProperty("oauth2.google.redirect-uri");
        String scope = "email profile"; // Scope는 고정값으로 설정

        String googleOauthUrl = "https://accounts.google.com/o/oauth2/v2/auth" +
                "?response_type=code" +
                "&client_id=" + URLEncoder.encode(clientId, StandardCharsets.UTF_8) +
                "&redirect_uri=" + URLEncoder.encode(redirectUri, StandardCharsets.UTF_8) +
                "&scope=" + URLEncoder.encode(scope, StandardCharsets.UTF_8);

        response.sendRedirect(googleOauthUrl);
    }
}