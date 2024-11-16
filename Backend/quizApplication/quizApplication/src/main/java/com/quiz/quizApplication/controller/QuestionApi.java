package com.quiz.quizApplication.controller;

import com.quiz.quizApplication.entity.Options;
import com.quiz.quizApplication.entity.Question;
import com.quiz.quizApplication.entity.User;
import com.quiz.quizApplication.exception.QuizException;
import com.quiz.quizApplication.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/question")
public class QuestionApi {
    @Autowired
    QuestionService questionService;

    @GetMapping("getQuestionDetails/{qstId}")
    public ResponseEntity<ApiResponse<Question>> getQuestionById(@PathVariable Long qstId) throws QuizException {
        Question question=questionService.getQuestionById(qstId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Question found", question));
    }
    @GetMapping("/{desp:[a-zA-Z &+-]*}")
    public ResponseEntity<ApiResponse<Question>> getQuestionFromDescription(@PathVariable String desp) throws QuizException {
        Question question=questionService.getQuestionByDescription(desp);
//        return ResponseEntity.ok(new ApiResponse<>(true, "Question found", question));
        return new ResponseEntity<>(new ApiResponse<>(true, "Question found", question),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<ApiResponse<Long>> saveQuestion(@RequestBody Question question){
        Long questionId= questionService.addQuestion(question);
        return ResponseEntity.ok(new ApiResponse<>(true,"Added question successfully",questionId));
    }
    @PutMapping("/updateQuestionOption")
    public ResponseEntity<ApiResponse<String>> addOptionsForQuestion(@RequestParam Long qstId, @RequestBody Options options) throws QuizException {
        questionService.addOptionForQuestion(qstId,options);
        return ResponseEntity.ok(new ApiResponse<>(true,"Successfully updated the option of the question","Option Updated"));
    }
    @DeleteMapping("/deleteQuestion/{qstId}")
    public ResponseEntity<ApiResponse<String>> deleteQuestion(@PathVariable Long qstId){
        questionService.deleteQuestionById(qstId);
        return ResponseEntity.ok(new ApiResponse<>(true,"Deleted the question with id: "+qstId,"Question is Deleted successfully"));
    }
    @DeleteMapping("/deleteOptionFromQuestion")
    public ResponseEntity<ApiResponse<String>> deleteOptionFromQuestion(@RequestParam Long qstId,@RequestParam Long optId) throws QuizException {
        String msg=questionService.deleteOptionFromQuestion(qstId,optId);
        return ResponseEntity.ok(new ApiResponse<>(true,"Deleted option successfully from question",msg));
    }
}
