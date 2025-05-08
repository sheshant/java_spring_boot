package com.telusco.quizapp.service;

import com.telusco.quizapp.model.Question;
import com.telusco.quizapp.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public List<Question>  getAllQuestions() {
        return questionDao.findAll();
    }

    public Optional<Question> getQuestionById(int id) {
        return questionDao.findById(id);
    }

    public List<Question> getQuestionByAnswer(String answer) {
        return questionDao.findByAnswer(answer);
    }

    public Question addQuestion(Question question) {
        return questionDao.save(question);
    }
}
