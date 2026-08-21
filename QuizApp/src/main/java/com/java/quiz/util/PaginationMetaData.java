package com.java.quiz.util;


import lombok.Data;

@Data
public class PaginationMetaData {

    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
    private boolean first;
    private boolean last;
    private long returnedElement;
}