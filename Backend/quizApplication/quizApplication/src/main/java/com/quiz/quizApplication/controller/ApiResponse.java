package com.quiz.quizApplication.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatusCode;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T>{
    private boolean success;
    private String message;
    private T data;

}

