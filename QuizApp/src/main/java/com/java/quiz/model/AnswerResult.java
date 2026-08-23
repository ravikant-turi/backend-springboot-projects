package com.java.quiz.model;

import lombok.Data;

@Data
public class AnswerResult {

    private Long questionId;

    private String questionName;

    private String submittedAns;

    private String correctAns;

    private boolean isCorrected;


}
