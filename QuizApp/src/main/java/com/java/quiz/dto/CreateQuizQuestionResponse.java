package com.java.quiz.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class CreateQuizQuestionResponse {

    private Long quizId;

    private Long questionId;

    private String content;

    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;

    private String correctAns;

    private String category;

    private String difficultyLevel;

}
