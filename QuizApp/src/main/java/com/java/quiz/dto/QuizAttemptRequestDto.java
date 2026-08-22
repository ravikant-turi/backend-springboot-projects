package com.java.quiz.dto;


import com.java.quiz.model.SubmittedAnswer;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class QuizAttemptRequestDto {

    private Long quizId;

    List<SubmittedAnswer> submittedAnswerList ;

}