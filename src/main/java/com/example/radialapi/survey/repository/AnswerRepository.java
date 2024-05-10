package com.example.radialapi.survey.repository;

import com.example.radialapi.survey.domain.Answer;
import com.example.radialapi.survey.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByQuestionId(Question question);

}
