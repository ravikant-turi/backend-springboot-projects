package com.java.quiz.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Quiz")
public class QuizQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quizId;

    private List<Question> questionList;

    private String QuizName;




}
