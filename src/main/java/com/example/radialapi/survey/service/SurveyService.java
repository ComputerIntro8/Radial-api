package com.example.radialapi.survey.service;

import com.example.radialapi.survey.domain.Answer;
import com.example.radialapi.survey.domain.Question;
import com.example.radialapi.survey.dto.TimeDataLevelDto;
import com.example.radialapi.survey.dto.AnswerDto;
import com.example.radialapi.survey.repository.AnswerRepository;
import com.example.radialapi.survey.repository.QuestionRepository;
import com.example.radialapi.survey.dto.response.AnswerQuestionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RequiredArgsConstructor
@Service
public class SurveyService {
    private static final Logger log = LoggerFactory.getLogger(SurveyService.class);


    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    public List<AnswerQuestionDto> getAnswerQuestion(){
        List<Question> questions = questionRepository.findAll();
        questions.forEach(question -> log.info(question.toString())); // 각 Question 객체의 toString() 결과를 로그로 출력

        // 각 문제에 대한 답변과 질문을 가져와서 AnswerQuestionDto로 매핑
        return questions.stream()
                .map(this::mapToAnswerQuestionDto)
                .collect(Collectors.toList());
    }
    private AnswerQuestionDto mapToAnswerQuestionDto(Question question) {
        // 해당 문제의 답변 가져오기
        List<Answer> answers = answerRepository.findByQuestionId(question);
        answers.forEach(answer -> log.info(answer.toString())); // 각 Answer 객체의 toString() 결과를 로그로 출력

        // question의 TimeDataLevels를 TimeDataLevelDto 리스트로 변환
        List<TimeDataLevelDto> timeDataLevelDtos = question.getTimeDataLevels().stream()
                .map(tdl ->
                        new TimeDataLevelDto(tdl.getTime(), tdl.getDataLevel()))
                /*
                    TimeDataLevelDto dto = new TimeDataLevelDto(tdl.getTime(), tdl.getDataLevel());
                    System.out.println("After mapping: Time = " + dto.getTime() + ", DataLevel = " + dto.getDataLevel());
                    return dto;
                */
                .collect(Collectors.toList());


        // AnswerQuestionDto에 답변과 질문 정보 및 시간-미세먼지 레벨 정보 매핑하여 반환
        return new AnswerQuestionDto(
                question.getId(),
                question.getQuestionText(),
                answers.stream()
                        .map(answer -> new AnswerDto(answer))
                        .collect(Collectors.toList()),
                timeDataLevelDtos
        );
    }

}
