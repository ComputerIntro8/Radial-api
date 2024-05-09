package com.example.radialapi.survey.dto.response;

import com.example.radialapi.survey.domain.Answer;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AnswerDto {
    private Long answerId;
    private String answerText;
    private boolean isCorrect;

    public AnswerDto(Answer entity) {
        this.answerId =entity.getId();
        this.answerText = entity.getAnswerText();
        this.isCorrect = entity.getCorrect();
    }

    public AnswerDto(Long id, String answerText, boolean correct) {
    }

    // entity -> dto


}
