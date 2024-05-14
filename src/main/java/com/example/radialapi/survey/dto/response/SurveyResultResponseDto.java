package com.example.radialapi.survey.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SurveyResultResponseDto {
    private Long surveyId;
    private Long userId;
    private Integer lineCount;
    private Integer radialCount;
    private Integer lineTimeTaken;
    private Integer radialTimeTaken;

    public SurveyResultResponseDto(Long surveyId, Long userId, Integer lineCount, Integer radialCount, Integer lineTimeTaken, Integer radialTimeTaken) {
        this.surveyId = surveyId;
        this.userId = userId;
        this.lineCount = lineCount;
        this.radialCount = radialCount;
        this.lineTimeTaken = lineTimeTaken;
        this.radialTimeTaken = radialTimeTaken;
    }
}