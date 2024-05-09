package com.example.radialapi.survey.dto.response;

import com.example.radialapi.survey.dto.response.AnswerDto;

import java.util.List;

public class AnswerQuestionDto {
    private Long questionId;
    private String questionText;
    private List<AnswerDto> answers;

    public AnswerQuestionDto(Long questionId, String questionText, List<AnswerDto> answers) {
        this.questionId = questionId;
        this.questionText = questionText;
        this.answers = answers;
    }
}
