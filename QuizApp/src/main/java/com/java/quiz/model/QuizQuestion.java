package com.java.quiz.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Quiz")
public class QuizQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quizId;

    @ManyToMany
    @JoinTable(
            name = "quiz_question",
            joinColumns = @JoinColumn(name = "quiz_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id")
    )
    private List<Question> questionList;

    private String QuizName;




}
