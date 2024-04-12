package com.example.radialapi.survey.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/survey")
public class SurveyController {

// private final SurveyService
// private final dto들

    // 문제 시작 (라인차트 or 방사형차트) GET 요청
    @Operation(summary = "문제정보 요청", description = "웹크롤링으로 가져온 데이터 문제 형태로 제공")
    @GetMapping("/{chart}")
    public void surveyGet(@RequestParam String chart){

    }

    // 문제 끝 -> 제출 POST 요청 ( survey DB, user DB 저장 )
    @Operation(summary = "설문결과 저장", description = "시간, 정답 개수 등등 저장")
    @PostMapping("/save")
    public void surveyPost(){
    }







}
