package com.java.quiz.dto;

import com.java.quiz.model.AnswerResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizAttemptResponseDto  <T> {

   private Long score;
   private Long totalQuestion;
   private String quizName;
   List<T> answerResults;

}
