package com.example.radialapi.survey.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class TimeDataLevelDto {
    private LocalDateTime time;
    private double dataLevel;

    public TimeDataLevelDto(LocalDateTime time, double dataLevel) {
        this.time = time;
        this.dataLevel = dataLevel;
    }
}
