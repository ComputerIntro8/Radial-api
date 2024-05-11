package com.example.radialapi.survey.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@ToString
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String questionText;

    @Column(nullable = true)
    private LocalDateTime time; // 시간

    @Column(nullable = false)
    private int dustLevel; // (예시) 미세먼지 농도

    @Builder
    public Question(String questionText, LocalDateTime time, int dustLevel) {
        this.questionText = questionText;
        this.time = time;
        this.dustLevel = dustLevel;
    }

    public Question(String content) {
        this.questionText = content;
    }

    public void update(String content) {
        this.questionText = content;
    }

}
