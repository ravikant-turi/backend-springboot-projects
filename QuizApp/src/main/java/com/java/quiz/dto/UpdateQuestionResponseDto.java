package com.java.quiz.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateQuestionResponseDto {

    private Long questionId;


    private String content;

    private Long questionNumber;


    private String optionA;

    private String optionB;

    private String optionC;

    private String optionD;

    private String category;

    private String difficultyLevel;

    private LocalDateTime updatedAt;

    private LocalDateTime createdAt;

}
