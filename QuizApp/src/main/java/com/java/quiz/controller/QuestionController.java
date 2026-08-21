package com.java.quiz.controller;

import com.java.quiz.dto.*;
import com.java.quiz.model.Question;
import com.java.quiz.payloads.ApiResponse;
import com.java.quiz.service.QuestionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/questions/")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping
    public ResponseEntity<ApiResponse<PagedResponse<GetQuestionResponseDto>>> findAllQuestion(
            HttpServletRequest httpServletRequest
    ,@RequestParam (defaultValue = "10")int size , @RequestParam (defaultValue = "0") int page) {
       ApiResponse<PagedResponse<GetQuestionResponseDto>> listApiResponse =
                this.questionService.findAll(size,page);
        listApiResponse.setPath(httpServletRequest.getRequestURI());
        return ResponseEntity.status(HttpStatus.OK).body(listApiResponse);
    }

    @GetMapping("{id}")
    ResponseEntity<ApiResponse<GetQuestionResponseDto>> findQuestionById(@PathVariable Long id,
                                                                         HttpServletRequest httpServletRequest) {

        ApiResponse<GetQuestionResponseDto> questionApiResponse = this.questionService.findQuestionById(id);
        questionApiResponse.setPath(httpServletRequest.getRequestURI());

        this.questionService.findAll(10,0);

        return ResponseEntity.status(HttpStatus.OK).body(questionApiResponse);

    }

    @DeleteMapping("{id}")
    ResponseEntity<ApiResponse<Void>> deleteQuestionById(@PathVariable Long id) {
        ApiResponse<Void> response = this.questionService.deleteQuestionById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @PostMapping
    public ResponseEntity<ApiResponse<CreateQuestionResponseDto>> createQuestion(
            @Valid @RequestBody CreateQuestionRequestDto requestDto,
            HttpServletRequest request) {
        ApiResponse<CreateQuestionResponseDto> response =
                questionService.createQuestion(requestDto);
        response.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @PutMapping
    public ResponseEntity<ApiResponse<UpdateQuestionResponseDto>> updateQuestion(
            @Valid @RequestBody UpdateQuestionRequestDto updateQuestionRequestDto ,HttpServletRequest httpServletRequest
    ) {

        ApiResponse<UpdateQuestionResponseDto> response =
                this.questionService.updateQuestionById(updateQuestionRequestDto);

        response.setPath(httpServletRequest.getRequestURI());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
