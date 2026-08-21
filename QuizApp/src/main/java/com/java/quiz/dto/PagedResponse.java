package com.java.quiz.dto;

import com.java.quiz.util.PaginationMetaData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagedResponse<T> {

    PaginationMetaData meta;
    List<T> items;


}
