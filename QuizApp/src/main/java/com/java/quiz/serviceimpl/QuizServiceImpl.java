package com.java.quiz.serviceimpl;

import com.java.quiz.QuizAppApplication;
import com.java.quiz.dto.QuizAttemptRequestDto;
import com.java.quiz.exception.ResourceNotFoundException;
import com.java.quiz.model.Question;
import com.java.quiz.model.Quiz;
import com.java.quiz.model.SubmittedAnswer;
import com.java.quiz.payloads.ApiResponse;
import com.java.quiz.repository.QuestionRepository;
import com.java.quiz.repository.QuizRepository;
import com.java.quiz.service.QuizService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private QuizRepository quizRepository;

    @Override
    public ApiResponse<List<Question>> CreateQuiz(String quizName, int numberOfQuestions, String category, String difficultyLevel) {

        System.out.println("----we are in the quiz services : " + difficultyLevel);
        Pageable pageable = PageRequest.of(0, numberOfQuestions);


        List<Question> questionList = questionRepository.makeQuestionOnDifficultyAndCategoryWise(category, difficultyLevel, pageable);
        if (questionList.size() < numberOfQuestions) {

            throw new ResourceNotFoundException(String.format("Only %d questions are available for the selected category and difficulty. " + "You requested %d questions.", questionList.size(), numberOfQuestions));

        }

        Quiz quiz = new Quiz();
        quiz.setQuestionList(questionList);
        quiz.setQuizName(quizName);
        quiz.setCreatedAt(LocalDateTime.now());
        quiz.setUpdatedAt(LocalDateTime.now());
        quiz.setDeleted(false);

        this.quizRepository.save(quiz);

        return new ApiResponse<>("Success", "DATA_FOUND", questionList);
    }

    @Override
    public List<Question> CreateQuizByTopic(String topic) {
        return null;
    }

    @Override
    public List<Question> CreateQuizByDifficulty(String difficulty) {
        List<Question> questionList = this.questionRepository.findByDifficultyLevel(difficulty);
        System.out.println(questionList);
        return questionList;
    }

    @Override
    public int attemptQuizAndReturnAns(QuizAttemptRequestDto quizAttemptRequestDto) {
        System.out.println("=======We are in the service ");
        int score = 0;
        for (SubmittedAnswer submittedAnswer : quizAttemptRequestDto.getSubmittedAnswerList()) {

            Question question = this.questionRepository.findById(submittedAnswer.getQuestionId()).orElseThrow(() -> new ResourceNotFoundException("Question does not exist"));


            System.out.println(submittedAnswer.getSubAns() + " : " + (question.getCorrectAns()));

            if (submittedAnswer.getSubAns().equalsIgnoreCase(question.getCorrectAns())) {
                score++;
            }
        }

        System.out.println("==================== "+score);
        return score;
    }
}
