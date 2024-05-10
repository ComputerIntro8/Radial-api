package com.example.radialapi.survey.service;

import com.example.radialapi.survey.domain.SurveyResult;
import com.example.radialapi.survey.dto.request.SurveyResultDto;
import com.example.radialapi.survey.repository.SurveyResultRepository;
import com.example.radialapi.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SurveyResultService {

    @Autowired
    private SurveyResultRepository surveyResultRepository;

    public void saveSurveyResult(SurveyResult surveyResult) {
        surveyResultRepository.save(surveyResult);
    }

    public SurveyResult createSurveyResultFromDto(SurveyResultDto dto, User user) {
        SurveyResult surveyResult = new SurveyResult();
        surveyResult.setUser(user);
        surveyResult.setLineCount(dto.getChartType().equalsIgnoreCase("line") ? dto.getCorrectAnswers() : null);
        surveyResult.setRadialCount(dto.getChartType().equalsIgnoreCase("radial") ? dto.getCorrectAnswers() : null);
        surveyResult.setLineTimeTaken(dto.getChartType().equalsIgnoreCase("line") ? dto.getTimeTaken() : null);
        surveyResult.setRadialTimeTaken(dto.getChartType().equalsIgnoreCase("radial") ? dto.getTimeTaken() : null);

        return surveyResult;
    }
}
