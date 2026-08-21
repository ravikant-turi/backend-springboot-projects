package com.java.quiz;

import com.java.quiz.repository.QuestionRepository;
import com.java.quiz.service.QuestionService;
import com.java.quiz.serviceimpl.QuestionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuizAppApplication {

//	QuestionService questionService;


	public static void main(String[] args) {

		SpringApplication.run(QuizAppApplication.class, args);
;
//		questionService.findAll(10,0);
	}

}
