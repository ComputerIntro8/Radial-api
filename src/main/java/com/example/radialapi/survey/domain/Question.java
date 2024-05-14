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
import java.util.List;

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

    @ManyToMany
    @JoinTable(
            name = "question_time_data_level",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "data_level_id")
    )
    private List<TimeDataLevel> timeDataLevels;

    public Question(String questionText, List<TimeDataLevel> timeDataLevels) {
        this.questionText = questionText;
        this.timeDataLevels = timeDataLevels;
    }

    public Question(String content) {
        this.questionText = content;
    }

    public void update(String content) {
        this.questionText = content;
    }

}
