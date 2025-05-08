package com.telusco.quizapp.wrapper;


import jakarta.persistence.Column;
import lombok.Data;

@Data
public class QuestionWrapper {
    private Integer id;

    private String question;
    private String option_a;
    private String option_b;
    private String option_c;
    private String option_d;

    public QuestionWrapper(Integer id, String question, String option_a, String option_b, String option_c, String option_d) {
        this.id = id;
        this.question = question;
        this.option_a = option_a;
        this.option_b = option_b;
        this.option_c = option_c;
        this.option_d = option_d;
    }
}
