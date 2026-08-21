package com.java.quiz.repository;

import com.java.quiz.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question,Long> {

  public List<Question> findByDifficultyLevel(String difficultyLevel);
}
