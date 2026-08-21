package com.java.quiz.serviceimpl;

import com.java.quiz.dto.*;
import com.java.quiz.exception.ResourceNotFoundException;
import com.java.quiz.model.Question;
import com.java.quiz.payloads.ApiResponse;
import com.java.quiz.repository.QuestionRepository;
import com.java.quiz.service.QuestionService;
import com.java.quiz.util.PaginationMetaData;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service

public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public ApiResponse<GetQuestionResponseDto> findQuestionById(Long id) {

        Question question = this.questionRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Question not found with this id " + id));
        GetQuestionResponseDto questionResponseDto = this.modelMapper.map(question, GetQuestionResponseDto.class);
        return new ApiResponse<GetQuestionResponseDto>(
                "Success", "DATA_FOUND", questionResponseDto);

    }

    @Override
    public ApiResponse<PagedResponse<GetQuestionResponseDto>> findAll(
            int size,
            int page) {

        // 1. Create Pageable object.
        // Page number is 0-based in Spring Data JPA.
        Pageable pageable = PageRequest.of(page, size);

        // 2. Fetch paginated data from database.
        // This returns both the current page data and pagination information.
        Page<Question> questionPage =
                questionRepository.findAll(pageable);


//        if (questionPage.getTotalPages() > 0
//                && page >= questionPage.getTotalPages()) {
//
//            throw new ResourceNotFoundException(
//                    "Requested page does not exist"
//            );
//        }

        // 3. Get only the questions from the current page.
        List<Question> questionList = questionPage.getContent();

        // 4. Convert Entity objects into Response DTOs.
        // We should not expose Entity directly to the client.
        List<GetQuestionResponseDto> questionDtoList =
                questionList.stream()
                        .map(question ->
                                modelMapper.map(
                                        question,
                                        GetQuestionResponseDto.class
                                )
                        )
                        .toList();

        // 5. Create pagination metadata.
        PaginationMetaData paginationMetaData =
                new PaginationMetaData();

        paginationMetaData.setPage(questionPage.getNumber());
        paginationMetaData.setSize(questionPage.getSize());
        paginationMetaData.setTotalElements(
                questionPage.getTotalElements()
        );
        paginationMetaData.setReturnedElement(questionPage.getNumberOfElements());
        paginationMetaData.setTotalPages(
                questionPage.getTotalPages()
        );
        paginationMetaData.setFirst(
                questionPage.isFirst()
        );
        paginationMetaData.setLast(
                questionPage.isLast()
        );

        // 6. Combine DTO list + pagination metadata.
        PagedResponse<GetQuestionResponseDto> pagedResponse =
                new PagedResponse<>(
                        paginationMetaData,
                        questionDtoList
                );

        // 7. Wrap everything inside common API response.
        return new ApiResponse<>(
                "SUCCESS",
                "DATA_FOUND",
                pagedResponse
        );
    }

    @Override
    public ApiResponse<Void> deleteQuestionById(Long id) {
        Question question = this.questionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Question not found with this id + " + id));


        this.questionRepository.delete(question);
        return new ApiResponse<>("Success", "DATA_DELETED_SUCCESSFULLY", null);
    }

    @Override
    public ApiResponse<UpdateQuestionResponseDto> updateQuestionById(
            UpdateQuestionRequestDto updateQuestionRequestDto) {

        Question question = this.questionRepository.findById(updateQuestionRequestDto.getQuestionId()).orElseThrow(() ->
                new ResourceNotFoundException("Question is not present with this id " + updateQuestionRequestDto.getQuestionId()));

        Question questionToBeSaved = modelMapper.map(updateQuestionRequestDto, Question.class);


        questionToBeSaved.setUpdatedAt(LocalDateTime.now());
        questionToBeSaved.setCreatedAt(LocalDateTime.now());
        questionToBeSaved.setQuestionNumber(question.getQuestionNumber());

        Question updatedQuestion = this.questionRepository.save(questionToBeSaved);


        return new ApiResponse<UpdateQuestionResponseDto>("Success", "DATA_UPDATED_SUCCESSFULLY",
                modelMapper.map(updatedQuestion, UpdateQuestionResponseDto.class));
    }

    @Override
    public ApiResponse<UpdateQuestionResponseDto> patchUpdateQuestion(UpdateQuestionRequestDto updateQuestionRequestDto) {
        return null;
    }

    @Override
    public ApiResponse<CreateQuestionResponseDto> createQuestion(
            CreateQuestionRequestDto createQuestionRequestDto) {


        Question question = modelMapper.map(
                createQuestionRequestDto,
                Question.class
        );


        System.out.println(question.getContent());
        question.setCreatedAt(LocalDateTime.now());
        question.setUpdatedAt(LocalDateTime.now());
        question.setDeleted(false);

        Question savedQuestion = questionRepository.save(question);

        CreateQuestionResponseDto responseDto =
                modelMapper.map(savedQuestion, CreateQuestionResponseDto.class);

        return new ApiResponse<CreateQuestionResponseDto>("SUCCESS", "DATA_CREATED", responseDto);
    }

}
