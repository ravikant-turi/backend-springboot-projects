package com.java.quiz.repository;

import com.java.quiz.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    public List<Question> findByDifficultyLevel(String difficultyLevel);

    @Query(value = "SELECT * FROM question ORDER BY RAND() LIMIT :count",
            nativeQuery = true)
    public List<Question> findRandomQuestions(@Param("count") int count);
}
