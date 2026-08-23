package com.java.quiz.serviceimpl;

import com.java.quiz.QuizAppApplication;
import com.java.quiz.dto.QuizAttemptRequestDto;
import com.java.quiz.dto.QuizAttemptResponseDto;
import com.java.quiz.exception.ResourceNotFoundException;
import com.java.quiz.model.AnswerResult;
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
import java.util.ArrayList;
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
    public  ApiResponse<QuizAttemptResponseDto<AnswerResult>> attemptQuizAndReturnAns(
            QuizAttemptRequestDto quizAttemptRequestDto) {
        System.out.println("=======We are in the service ");
        long score = 0;
         QuizAttemptResponseDto <AnswerResult> responseDto=new QuizAttemptResponseDto();



         List<AnswerResult> answerResultList=new ArrayList<>();

        for (SubmittedAnswer submittedAnswer :
                quizAttemptRequestDto.getSubmittedAnswerList()) {

            AnswerResult answerResult=new AnswerResult();

            Question question = this.questionRepository.findById(submittedAnswer.getQuestionId()).orElseThrow(() -> new ResourceNotFoundException("Question does not exist"));


            System.out.println(submittedAnswer.getSubAns() + " : " + (question.getCorrectAns()));

            if (submittedAnswer.getSubAns().equalsIgnoreCase(question.getCorrectAns())) {
                score++;
                answerResult.setCorrected(true);
            }
            answerResult.setCorrectAns(submittedAnswer.getSubAns());
            answerResult.setQuestionId(submittedAnswer.getQuestionId());
            answerResult.setQuestionName(question.getCategory());
            answerResult.setQuestionId(question.getQuestionId());
            answerResult.setSubmittedAns(submittedAnswer.getSubAns());
            answerResultList.add(answerResult);

        }


          String   quizName=this.quizRepository.findById(quizAttemptRequestDto.getQuizId())
                    .orElseThrow(()-> new ResourceNotFoundException("Quiz is not found with quiz id ")).getQuizName();
        responseDto.setQuizName(quizName);
        responseDto.setAnswerResults(answerResultList);
        responseDto.setScore(score);
        responseDto.setTotalQuestion((long) answerResultList.size()) ;

        System.out.println("==================== "+score);


     return new ApiResponse<>("Success","DATA_FOUND",responseDto);

    }
}
