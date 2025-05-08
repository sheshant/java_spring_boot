package com.telusco.quizapp.controller;


import com.telusco.quizapp.model.Quiz;
import com.telusco.quizapp.service.QuizService;
import com.telusco.quizapp.wrapper.QuestionWrapper;
import com.telusco.quizapp.wrapper.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<Quiz> createQuiz(@RequestParam String title,
                                           @RequestParam int numberOfQuestions) {

        System.out.println(
                "Title: " + title + ", Number of Questions: " + numberOfQuestions
        );
        return quizService.createQuiz(title, numberOfQuestions);
    }

    @GetMapping("getQuizData/{id}")
    public ResponseEntity<Quiz> getQuizData(@PathVariable int id) {
        return quizService.getQuizData(id);
    }

    @GetMapping("getAllQuizzes")
    public ResponseEntity<List<Quiz>> getAllQuizzes() {
        return quizService.getAllQuizzes();
    }

    @GetMapping("getQuiz/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable int id) {
        return quizService.getQuiz(id);
    }
    @PostMapping("submitQuiz/{id}")
    public ResponseEntity<String> submitQuiz(@PathVariable int id, @RequestBody List<ResponseWrapper> responseWrappers) {
        return quizService.submitQuiz(id, responseWrappers);
    }

}
