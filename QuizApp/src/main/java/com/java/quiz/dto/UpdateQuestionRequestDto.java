package com.java.quiz.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateQuestionRequestDto {

    @NotNull(message = "Question is required")
    @Positive(message = "Question number should be positive")
    private Long questionId;


    @NotBlank(message = "Question is required")
    @Size(min = 3, max = 100, message = "Question must be between 3 and 100 characters ")
    private String content;

    @NotBlank(message = "correctAns is required")
    @Size(min = 1, max = 100, message = "CorrectAns must be between 1 and 100")
    private String correctAns;

    @NotBlank(message = "optionA is required")
    @Size(min = 1, max = 100, message = "optionA must be between 1 and 100")
    private String optionA;

    @NotBlank(message = "optionB is required")
    @Size(min = 1, max = 100, message = "optionB must be between 1 and 100")

    private String optionB;
    @NotBlank(message = "optionC is required")
    @Size(min = 1, max = 100, message = "optionC must be between 1 and 100")

    private String optionC;
    @NotBlank(message = "optionD is required")
    @Size(min = 1, max = 100, message = "optionD must be between 1 and 100")

    private String optionD;
    @NotBlank(message = "category is required")
    @Size(min = 1, max = 100, message = "category must be between 1 and 100")

    private String category;
    @NotBlank(message = "difficultyLevel is required")

    @Size(min = 1, max = 100, message = "difficultyLevel must be between 1 and 100")
    private String difficultyLevel;


}


