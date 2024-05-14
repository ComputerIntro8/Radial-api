package com.example.radialapi.survey.dto.request;

import com.example.radialapi.survey.domain.SurveyResult;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SurveyResultDto {
    private String chartType;  // 차트 종류 (radial 또는 line)
    private Long questionId;  // 문제 번호
    private Long answerId;  // 보기 번호
    private int timeTaken;  // 소요시간


    public SurveyResultDto(String chartType, Long questionId, Long answerId, int timeTaken) {
        this.chartType = chartType;
        this.questionId = questionId;
        this.answerId = answerId;
        this.timeTaken = timeTaken;
    }

    public SurveyResult toEntity(){
        return SurveyResult.builder().build();
    }

}