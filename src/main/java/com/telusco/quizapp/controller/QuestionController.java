package com.telusco.quizapp.controller;

import com.telusco.quizapp.model.Question;
import com.telusco.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<List<Question>>(questionService.getAllQuestions(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
//            return new ResponseEntity<List<Question>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("questionById/{id}")
    public Optional<Question> getQuestionById(@PathVariable int id) {
        return questionService.getQuestionById(id);
    }

    @GetMapping("questionByAnswer/{answer}")
    public List<Question> getQuestionByAnswer(@PathVariable String answer) {
        return questionService.getQuestionByAnswer(answer);
    }

    @PostMapping("addQuestion")
    public Question addQuestion(Question question) {
        return questionService.addQuestion(question);
    }
}
