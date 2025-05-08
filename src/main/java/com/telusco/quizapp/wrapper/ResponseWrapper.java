package com.telusco.quizapp.wrapper;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ResponseWrapper {
    private Integer id;

    private String answer;

    public ResponseWrapper(Integer id, String answer) {
        this.id = id;
        this.answer = answer;
    }
}
