package com.example.radialapi.survey.repository;

import com.example.radialapi.survey.domain.Answer;
import com.example.radialapi.survey.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByQuestionId(Question question);

}
