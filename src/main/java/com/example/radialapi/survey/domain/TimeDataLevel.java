package com.example.radialapi.survey.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class TimeDataLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    private LocalDateTime time;
    private int dataLevel;


    public TimeDataLevel(Question question, LocalDateTime time, int dataLevel) {
        this.question = question;
        this.time = time;
        this.dataLevel = dataLevel;
    }
}
