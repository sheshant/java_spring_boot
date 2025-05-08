package com.telusco.quizapp.dao;


import com.telusco.quizapp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM question ORDER BY RANDOM() LIMIT :numberOfQuestions")
    List<Question> findRandomQuestions(int numberOfQuestions);
//    List<Question> findAll();

    List<Question> findByAnswer(String answer);
    // This interface will automatically provide CRUD operations for the Question entity
    // You can add custom query methods here if needed
}
