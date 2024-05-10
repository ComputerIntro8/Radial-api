package com.example.radialapi.user.controller;


import com.example.radialapi.user.domain.User;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {


    @Operation(summary = "유저정보 요청", description = "유저 아이디로 유저 정보 제공하는 api")
    @GetMapping("/{id}")
    public void getUserData(@RequestParam String id){
    }



}
