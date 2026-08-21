package com.java.quiz.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @Column(name = "content")
    private String content;

    @Column( name = "questionNumber")
    private Long questionNumber;

    private String correctAns;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    @Column(name = "category")
    private String category;
    private String difficultyLevel;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
    private boolean isDeleted;
}
