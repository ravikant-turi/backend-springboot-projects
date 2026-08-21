package com.java.quiz.service;

import com.java.quiz.dto.*;

import com.java.quiz.model.Question;
import com.java.quiz.payloads.ApiResponse;
import com.java.quiz.util.PaginationMetaData;

import java.util.List;

public interface QuestionService {

    ApiResponse<GetQuestionResponseDto> findQuestionById(Long id);

    ApiResponse<PagedResponse<GetQuestionResponseDto>>findAll(int size, int page);

    ApiResponse<Void> deleteQuestionById(Long id);

    ApiResponse<UpdateQuestionResponseDto> updateQuestionById(
            UpdateQuestionRequestDto updateQuestionRequestDto);

    ApiResponse<UpdateQuestionResponseDto> patchUpdateQuestion(
            UpdateQuestionRequestDto updateQuestionRequestDto);

    ApiResponse<CreateQuestionResponseDto> createQuestion(
            CreateQuestionRequestDto createQuestionRequestDto);

}
