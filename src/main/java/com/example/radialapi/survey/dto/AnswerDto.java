package com.example.radialapi.survey.dto;

import com.example.radialapi.survey.domain.Answer;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AnswerDto {
    private Long answerId;
    private String answerText;

    public AnswerDto(Answer entity) {
        this.answerId =entity.getId();
        this.answerText = entity.getAnswerText();
    }

    public AnswerDto(Long id, String answerText, boolean correct) {
    }

    // entity -> dto


}
