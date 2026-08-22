package com.java.quiz.serviceimpl;

import com.java.quiz.model.Question;
import com.java.quiz.repository.QuestionRepository;
import com.java.quiz.service.QuizService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class QuizServiceImpl implements QuizService {

  @Autowired
  private QuestionRepository questionRepository;
    @Override
    public List<Question> CreateQuiz(int numberOfQuestions) {

     List<Question> questionList=   this.questionRepository.findRandomQuestions(numberOfQuestions);
        if (questionList.size() < numberOfQuestions) {
            throw new RuntimeException(
                    "Not enough questions available"
            );
        }

        return questionList;
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
