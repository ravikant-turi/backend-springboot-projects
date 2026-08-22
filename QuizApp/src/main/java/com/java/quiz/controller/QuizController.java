package com.java.quiz.controller;

import com.java.quiz.dto.QuizAttemptRequestDto;
import com.java.quiz.model.Question;
import com.java.quiz.payloads.ApiResponse;
import com.java.quiz.service.QuizService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/quizes")
public class QuizController {
    @Autowired
    private QuizService quizService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Question>>> createRandomQuiz(HttpServletRequest httpServletRequest, @RequestParam(defaultValue = "5") int totalQuestion, @RequestParam(defaultValue = "java") String category, @RequestParam(defaultValue = "easy", required = false) String difficultyLevel
            , @RequestParam(defaultValue = "New Quiz") String quizName) {

        System.out.println("------we are in the controller n: " + difficultyLevel);
        ApiResponse<List<Question>> questionList = this.quizService.CreateQuiz(quizName, totalQuestion, category, difficultyLevel);
        questionList.setPath(httpServletRequest.getRequestURI());
        return ResponseEntity.status(HttpStatus.CREATED).body(questionList);


    }

    @GetMapping("{difficultyLevel}")
    public ResponseEntity<List<Question>> createQuiz(@PathVariable String difficultyLevel) {
        List<Question> questionApiResponse = this.quizService.CreateQuizByDifficulty(difficultyLevel);
        return ResponseEntity.status(HttpStatus.OK).body(questionApiResponse);
    }

    @PostMapping("/attempt")
    ResponseEntity<Integer> attemptQuiz(@RequestBody QuizAttemptRequestDto quizAttemptRequestDto){

        int ans=this.quizService.attemptQuizAndReturnAns(quizAttemptRequestDto);

        System.out.println("==========we are in the controller");

        return  ResponseEntity.status(HttpStatus.OK).body(ans);
    }

}



