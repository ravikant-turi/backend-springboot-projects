package com.java.quiz.controller;

import com.java.quiz.model.Question;
import com.java.quiz.payloads.ApiResponse;
import com.java.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/quiz")
public class QuizController {
    @Autowired
    private QuizService quizService;

    @GetMapping("{difficultyLevel}")
    public ResponseEntity<List<Question>> createQuiz(@PathVariable String difficultyLevel) {
        List<Question> questionApiResponse = this.quizService.CreateQuizByDifficulty(difficultyLevel);
     return ResponseEntity.status(HttpStatus.OK).body(questionApiResponse);
}
}
