package com.example.radialapi.authorization.oauth.service;


import com.example.radialapi.authorization.jwt.AuthTokensGenerator;
import com.example.radialapi.authorization.jwt.dto.AuthTokens;
import com.example.radialapi.user.domain.Role;
import com.example.radialapi.user.domain.User;
import com.example.radialapi.user.repository.UserRepository;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
public class LoginService {
    private final Environment env;
    private final RestTemplate restTemplate = new RestTemplate();
    private final UserRepository userRepository;
    private final AuthTokensGenerator authTokensGenerator;

    public AuthTokens socialLogin (String registrationId, String code) {
        //      registrationId (Google 정보) + code (Authorization Code)로 AccessToken을 얻어서
    //      AccessToken으로 사용자 정보 가져오는 것까지 구현 완
        String accessToken = getAccessToken(registrationId, code);
        JsonNode userResourceNode = getUserResource(registrationId, accessToken);

        String name = userResourceNode.get("name").asText();
        String email = userResourceNode.get("email").asText();
        String picture = userResourceNode.get("picture").asText();
        User user = userRepository.findByEmail(email)
                .orElseGet(()->{
                    User newUser = User.builder()
                            .name(name)
                            .email(email)
                            .picture(picture)
                            .role(Role.USER) // 일반 사용자
                            .build();
                    return userRepository.save(newUser);
                });

//      사용자 정보로 jwt 토큰 발행 ( + refreshToken )
        return authTokensGenerator.generate(user.getId());
    }


    private String getAccessToken(String registrationId, String authorizationCode) {
        String clientId = env.getProperty("oauth2." + registrationId + ".client-id");
        String clientSecret = env.getProperty("oauth2." + registrationId + ".client-secret");
        String redirectUri = env.getProperty("oauth2." + registrationId + ".redirect-uri");
        String tokenUri = env.getProperty("oauth2." + registrationId + ".token-uri");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("code", authorizationCode);
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("redirect_uri", redirectUri);
        params.add("grant_type", "authorization_code");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity entity = new HttpEntity(params, headers);

        ResponseEntity<JsonNode> responseNode = restTemplate.exchange(tokenUri, HttpMethod.POST, entity, JsonNode.class);
        JsonNode accessTokenNode = responseNode.getBody();
        return accessTokenNode.get("access_token").asText();
    }
    public JsonNode getUserResource(String registrationId, String accessToken) {
        String resourceUri = env.getProperty("oauth2." + registrationId + ".resource-uri");


        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        HttpEntity entity = new HttpEntity(headers);

        return restTemplate.exchange(resourceUri, HttpMethod.GET, entity, JsonNode.class).getBody();
    }
}
