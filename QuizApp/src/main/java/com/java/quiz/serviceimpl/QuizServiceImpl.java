package com.java.quiz.serviceimpl;

import com.java.quiz.model.Question;
import com.java.quiz.repository.QuestionRepository;
import com.java.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {

  @Autowired
  private QuestionRepository questionRepository;
    @Override
    public List<Question> CreateQuiz() {
        return null;
    }

    @Override
    public List<Question> CreateQuizByTopic(String topic) {
        return null;
    }

    @Override
    public List<Question> CreateQuizByDifficulty(String difficulty) {
        List<Question> questionList=this.questionRepository.findByDifficultyLevel(difficulty);
        System.out.println(questionList);
        return questionList;
    }
}
