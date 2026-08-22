package com.java.quiz.service;

import com.java.quiz.dto.QuizAttemptRequestDto;
import com.java.quiz.model.Question;
import com.java.quiz.payloads.ApiResponse;

import java.util.List;

public interface QuizService {

//    createQuiz()
//├── findById()
//├── findAll()
//├── updateQuiz()
//├── deleteQuiz()
//├── addQuestionsToQuiz()
//└── removeQuestionFromQuiz()

   ApiResponse<List<Question>> CreateQuiz(String quizName ,int totalQuestion , String category , String difficultyLevel);
    List<Question> CreateQuizByTopic(String topic);
    List<Question> CreateQuizByDifficulty(String difficulty);

    int attemptQuizAndReturnAns(QuizAttemptRequestDto quizAttemptRequestDto);
}
