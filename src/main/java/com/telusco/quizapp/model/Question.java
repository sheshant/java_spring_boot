package com.telusco.quizapp.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 1000)
    private String question;
    private String option_a;
    private String option_b;
    private String option_c;
    private String option_d;
    private String answer;
}
