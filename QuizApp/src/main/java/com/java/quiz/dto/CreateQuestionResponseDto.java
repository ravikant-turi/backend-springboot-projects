package com.java.quiz.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class CreateQuestionResponseDto {


    private String content;

    private Long questionNumber;

    private String correctAns;

    private String optionA;

    private String optionB;

    private String optionC;

    private String optionD;

    private String category;

    private String difficultyLevel;

    private LocalDateTime updatedAt;

    private LocalDateTime createdAt;


}

