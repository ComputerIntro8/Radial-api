package com.example.radialapi.survey.controller;

import com.example.radialapi.survey.domain.SurveyResult;
import com.example.radialapi.survey.dto.request.SurveyResultDto;
import com.example.radialapi.survey.dto.response.AnswerQuestionDto;
import com.example.radialapi.survey.service.SurveyResultService;
import com.example.radialapi.survey.service.SurveyService;
import com.example.radialapi.user.domain.User;
import com.example.radialapi.user.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/survey")
public class SurveyController {

    private final SurveyService surveyService;
    private final SurveyResultService surveyResultService;
    private final UserRepository userRepository; // 일반적으로는 권장 X


    // 문제 시작(요청) -> 문제 + 보기 데이터 반환
    @Operation(summary = "문제정보 요청", description = "문제DB에서 문제+답 가져와서 전달")
    @GetMapping()
    public ResponseEntity<List<AnswerQuestionDto>> getQuiz(){
        List<AnswerQuestionDto> answerQuestionData = surveyService.getAnswerQuestion();
        return ResponseEntity.ok().body(answerQuestionData);
    }

    // 문제 끝 -> 제출 POST 요청 ( survey DB, user DB 저장 )
    @Operation(summary = "설문결과 저장", description = "차트 종류별 시간, 정답 개수 등등 저장")
    @PostMapping("/save/{userId}")
    public ResponseEntity<?> saveSurveyResult(@PathVariable Long userId, @RequestBody SurveyResultDto surveyResultDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        SurveyResult surveyResult = surveyResultService.createSurveyResultFromDto(surveyResultDto, user);
        surveyResultService.saveSurveyResult(surveyResult);
        return ResponseEntity.ok().build();
    }
}
