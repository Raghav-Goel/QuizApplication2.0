package com.quiz.quizApplication.controller;

import com.quiz.quizApplication.entity.Quiz;
import com.quiz.quizApplication.exception.QuizException;
import com.quiz.quizApplication.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizApi {
    @Autowired
    QuizService quizService;

    @GetMapping("/getQuiz/{quizId}")
    public ResponseEntity<ApiResponse<Quiz>> getQuizById(@PathVariable Long quizId) throws QuizException {
        Quiz quiz=quizService.getQuizById(quizId);
        return ResponseEntity.ok(new ApiResponse<>(true,"Quiz fetched through quiz id successfully.",quiz));
    }
    @PostMapping("/createQuiz")
    public ResponseEntity<ApiResponse<Long>> createQuiz(@RequestBody Quiz quiz){
        Long quizId= quizService.createQuiz(quiz);
        return new ResponseEntity<>(new ApiResponse<>(true,"Quiz created successfully",quizId), HttpStatus.CREATED);
    }
    @PutMapping("/addQuestionToQuiz")
    public ResponseEntity<ApiResponse<String>> addQuestionToQuiz(@RequestParam Long quizId,@RequestBody List<Long> qstIdList) throws QuizException {
        String msg=quizService.addQuestionToQuiz(quizId,qstIdList);
        return ResponseEntity.ok(new ApiResponse<>(true,"Added question to quiz",msg));
    }
}
