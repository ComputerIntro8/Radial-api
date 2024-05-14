package com.example.radialapi.survey.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class TimeDataLevelDto {
    private LocalDateTime time;
    private int dataLevel;

    public TimeDataLevelDto(LocalDateTime time, int dataLevel) {
        this.time = time;
        this.dataLevel = dataLevel;
    }
}
