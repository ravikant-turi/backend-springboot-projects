package com.java.quiz.service;

import com.java.quiz.model.Question;

import java.util.List;

public interface QuizService {

//    createQuiz()
//├── findById()
//├── findAll()
//├── updateQuiz()
//├── deleteQuiz()
//├── addQuestionsToQuiz()
//└── removeQuestionFromQuiz()

   List<Question> CreateQuiz(int totalQuestion);
    List<Question> CreateQuizByTopic(String topic);
    List<Question> CreateQuizByDifficulty(String difficulty);

}
