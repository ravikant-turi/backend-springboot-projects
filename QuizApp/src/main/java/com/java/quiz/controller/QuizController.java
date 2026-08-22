package com.java.quiz.controller;

import com.java.quiz.model.Question;
import com.java.quiz.payloads.ApiResponse;
import com.java.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/quizs/")
public class QuizController {
    @Autowired
    private QuizService quizService;

    @GetMapping
    public ResponseEntity<List<Question>>createRandomQuiz(@RequestParam (defaultValue = "5")
                                                              int n){

        System.out.println("we are in the controller n: "+n);
        return ResponseEntity.status(HttpStatus.CREATED).
                body(this.quizService.CreateQuiz(n));


    }

    @GetMapping("{difficultyLevel}")
    public ResponseEntity<List<Question>> createQuiz(@PathVariable String difficultyLevel) {
        List<Question> questionApiResponse = this.quizService.CreateQuizByDifficulty(difficultyLevel);
     return ResponseEntity.status(HttpStatus.OK).body(questionApiResponse);
}
}
