package com.example.radialapi.survey.dto.request;

import com.example.radialapi.survey.domain.SurveyResult;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SurveyResultDto {
    private String chartType;  // 차트 종류 (radial 또는 line)
    private int correctAnswers;  // 정답 개수
    private int timeTaken;  // 소요시간

    public SurveyResultDto(String chartType, int correctAnswers, int timeTaken) {
        this.chartType = chartType;
        this.correctAnswers = correctAnswers;
        this.timeTaken = timeTaken;
    }

    public SurveyResult toEntity(){
        return SurveyResult.builder().build();
    }

}