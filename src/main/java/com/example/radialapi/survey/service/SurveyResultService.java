package com.example.radialapi.survey.service;

import com.example.radialapi.survey.domain.Answer;
import com.example.radialapi.survey.domain.SurveyResult;
import com.example.radialapi.survey.dto.request.SurveyResultDto;
import com.example.radialapi.survey.dto.response.SurveyResultResponseDto;
import com.example.radialapi.survey.repository.AnswerRepository;
import com.example.radialapi.survey.repository.SurveyResultRepository;
import com.example.radialapi.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SurveyResultService {

    private final AnswerRepository answerRepository;
    private final SurveyResultRepository surveyResultRepository;

    public SurveyResultResponseDto saveSurveyResults(User user, List<SurveyResultDto> surveyResultsDtos) {
        int lineCount = 0;
        int radialCount = 0;
        int lineTimeTaken = 0;
        int radialTimeTaken = 0;

        for (SurveyResultDto dto : surveyResultsDtos) {
            Answer answer = answerRepository.findById(dto.getAnswerId())
                    .orElseThrow(() -> new IllegalStateException("Answer not found"));
            if (!answer.getQuestionId().getId().equals(dto.getQuestionId())) {

                throw new IllegalStateException("Question ID does not match");
            }

            if (answer.getCorrect()) {
                if ("line".equalsIgnoreCase(dto.getChartType())) {
                    lineCount += 1;
                    lineTimeTaken += dto.getTimeTaken();
                } else if ("radial".equalsIgnoreCase(dto.getChartType())) {
                    radialCount += 1;
                    radialTimeTaken += dto.getTimeTaken();
                }
            }
            else{
                if ("line".equalsIgnoreCase(dto.getChartType())) {
                    lineTimeTaken += dto.getTimeTaken();
                } else if ("radial".equalsIgnoreCase(dto.getChartType())) {
                    radialTimeTaken += dto.getTimeTaken();
                }
            }
        }

        SurveyResult surveyResult = SurveyResult.builder()
                .user(user)
                .lineCount(lineCount)
                .radialCount(radialCount)
                .lineTimeTaken(lineTimeTaken)
                .radialTimeTaken(radialTimeTaken)
                .build();

        surveyResultRepository.save(surveyResult);

        return new SurveyResultResponseDto(
                surveyResult.getSurveyId(),
                surveyResult.getUser().getId(),
                surveyResult.getLineCount(),
                surveyResult.getRadialCount(),
                surveyResult.getLineTimeTaken(),
                surveyResult.getRadialTimeTaken()
        );
    }
}