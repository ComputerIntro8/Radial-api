package com.example.radialapi.survey.service;

import com.example.radialapi.survey.domain.Answer;
import com.example.radialapi.survey.domain.Question;
import com.example.radialapi.survey.dto.response.AnswerDto;
import com.example.radialapi.survey.repository.AnswerRepository;
import com.example.radialapi.survey.repository.QuestionRepository;
import com.example.radialapi.survey.dto.response.AnswerQuestionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SurveyService {
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    public List<AnswerQuestionDto> getAnswerQuestion(){
        // 모든 문제를 가져옴
        List<Question> questions = questionRepository.findAll();

        // 각 문제에 대한 답변과 질문을 가져와서 AnswerQuestionDto로 매핑
        return questions.stream()
                .map(this::mapToAnswerQuestionDto)
                .collect(Collectors.toList());
    }
    private AnswerQuestionDto mapToAnswerQuestionDto(Question question) {
        // 해당 문제의 답변 가져오기
        List<Answer> answers = answerRepository.findByQuestionId(question);

        // AnswerQuestionDto에 답변과 질문 정보 매핑하여 반환
        return new AnswerQuestionDto(
                question.getId(),
                question.getQuestionText(),
                answers.stream()
                        .map(answer -> new AnswerDto(answer))
                        .collect(Collectors.toList())
        );
    }

}
