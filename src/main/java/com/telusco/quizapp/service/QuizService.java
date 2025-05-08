package com.telusco.quizapp.service;

import com.telusco.quizapp.dao.QuestionDao;
import com.telusco.quizapp.dao.QuizDao;
import com.telusco.quizapp.model.Question;
import com.telusco.quizapp.model.Quiz;
import com.telusco.quizapp.wrapper.QuestionWrapper;
import com.telusco.quizapp.wrapper.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;
    @Autowired
    QuestionDao questionDao;


    public ResponseEntity<Quiz> createQuiz(String title, int numberOfQuestions) {

        List<Question> questionList = questionDao.findRandomQuestions(numberOfQuestions);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questionList);
        quizDao.save(quiz);

        return new ResponseEntity<Quiz>(quiz, HttpStatus.CREATED);
    }

    public ResponseEntity<Quiz> getQuizData(int id) {
        Quiz quiz = quizDao.findById(id).orElse(null);
        if (quiz != null) {
            return new ResponseEntity<Quiz>(quiz, HttpStatus.OK);
        } else {
            return new ResponseEntity<Quiz>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<List<Quiz>> getAllQuizzes() {
        List<Quiz> quizzes = quizDao.findAll();
        if (!quizzes.isEmpty()) {
            return new ResponseEntity<List<Quiz>>(quizzes, HttpStatus.OK);
        } else {
            return new ResponseEntity<List<Quiz>>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<List<QuestionWrapper>> getQuiz(int id) {
        Quiz quiz = quizDao.findById(id).orElse(null);
        if (quiz != null) {
            List<QuestionWrapper> questionWrappers = quiz.getQuestions().stream()
                    .map(question -> new QuestionWrapper(
                            question.getId(),
                            question.getQuestion(),
                            question.getOption_a(),
                            question.getOption_b(),
                            question.getOption_c(),
                            question.getOption_d())
                    ).toList();
            return new ResponseEntity<List<QuestionWrapper>>(questionWrappers, HttpStatus.OK);
        } else {
            return new ResponseEntity<List<QuestionWrapper>>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<String> submitQuiz(int id, List<ResponseWrapper> responseWrappers) {
        Quiz quiz = quizDao.findById(id).orElse(null);
        if (quiz != null) {
            int score = 0;
            for (ResponseWrapper response : responseWrappers) {
                Question question = questionDao.findById(response.getId()).orElse(null);
                if (question != null && question.getAnswer().equalsIgnoreCase(response.getAnswer())) {
                    score++;
                }
            }
            return new ResponseEntity<String>("Your score is: " + score, HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Quiz not found", HttpStatus.NOT_FOUND);
        }
    }
}
