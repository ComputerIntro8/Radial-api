package com.example.radialapi.survey.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class TimeDataLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "timeDataLevels")
    private List<Question> questions;

    private LocalDateTime time;
    private Double dataLevel;


    // 기본 생성자에서 Question을 제외하고 time과 dataLevel만 받습니다.
    public TimeDataLevel(LocalDateTime time, Double dataLevel) {
        this.time = time;
        this.dataLevel = dataLevel;
    }

    // Question 객체들을 추가하는 메소드
    public void addQuestion(Question question) {
        this.questions.add(question);
        if (!question.getTimeDataLevels().contains(this)) {
            question.getTimeDataLevels().add(this);
        }
    }
}
