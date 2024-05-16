package com.example.radialapi.survey.repository;

import com.example.radialapi.survey.domain.Question;
import com.example.radialapi.survey.domain.SurveyResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurveyResultRepository extends JpaRepository<SurveyResult, Long> {
    List<SurveyResult> findByUserId(Long userId);
}
