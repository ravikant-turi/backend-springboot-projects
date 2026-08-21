package com.java.quiz.payloads;

import com.java.quiz.dto.GetQuestionResponseDto;
import com.java.quiz.util.PaginationMetaData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {

    private LocalDateTime timestamp;
    private String path;
    private T data;
    private String status;
    private String message;

    public ApiResponse(String status, String message, T data) {
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.data = data;
        this.status = status;
    }


}