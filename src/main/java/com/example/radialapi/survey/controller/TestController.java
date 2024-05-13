package com.example.radialapi.survey.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.ui.Model;

@RequiredArgsConstructor
@RequestMapping("/api/survey")
@Controller
public class TestController {

    // SSR
    @GetMapping("/view")
    public String getSurveyPage(Model model) {
        //   List<AnswerQuestionDto> answerQuestionData = surveyService.getAnswerQuestion();
        //   model.addAttribute("questions", answerQuestionData);
        return "survey.html";
    }

}
