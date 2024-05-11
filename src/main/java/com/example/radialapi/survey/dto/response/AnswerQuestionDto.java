package com.example.radialapi.survey.dto.response;

import com.example.radialapi.survey.dto.response.AnswerDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class AnswerQuestionDto {
    private Long questionId;
    private String questionText;
    private List<AnswerDto> answers;
    private LocalDateTime time;
    private int dustLevel;

    // 생성자는 모든 필드를 초기화합니다
    public AnswerQuestionDto(Long questionId, String questionText, List<AnswerDto> answers, LocalDateTime time, int dustLevel) {
        this.questionId = questionId;
        this.questionText = questionText;
        this.answers = answers;
        this.time = time;
        this.dustLevel = dustLevel;
    }
}
