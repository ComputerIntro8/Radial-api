package com.example.radialapi.survey.domain;

import com.example.radialapi.user.domain.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class SurveyResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long surveyId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false) // 사용자 ID를 외래 키로 설정
    private User user;  // 직접 참조

    @Column(nullable = true)
    private Integer lineCount;

    @Column(nullable = true)
    private Integer radialCount;

    @Column(nullable = true)
    private Integer lineTimeTaken;

    @Column(nullable = true)
    private Integer radialTimeTaken;

    @Builder
    public SurveyResult(User user, Integer lineCount, Integer radialCount, Integer lineTimeTaken, Integer radialTimeTaken) {
        this.user = user;
        this.lineCount = lineCount;
        this.radialCount = radialCount;
        this.lineTimeTaken = lineTimeTaken;
        this.radialTimeTaken = radialTimeTaken;
    }
}
