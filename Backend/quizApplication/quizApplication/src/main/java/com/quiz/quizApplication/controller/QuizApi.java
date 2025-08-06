package com.quiz.quizApplication.controller;

import com.quiz.quizApplication.Utility.PageResponse;
import com.quiz.quizApplication.Utility.Response;
import com.quiz.quizApplication.entity.Question;
import com.quiz.quizApplication.entity.Quiz;
import com.quiz.quizApplication.exception.QuizException;
import com.quiz.quizApplication.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/quiz")
public class QuizApi {
    @Autowired
    QuizService quizService;
    @GetMapping("/getAllQuestions")
    public ResponseEntity<ApiResponse<PageResponse<Quiz>>> getAllQuestions(
            @RequestParam(value = "pageNumber",defaultValue = "0",required = false) int pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "5",required = false) int pageSize
    ){
        PageResponse<Quiz> pageResponse=quizService.getAllQuiz(pageNumber,pageSize);
        return new ResponseEntity<>(new ApiResponse<>(true,"Getting all quiz",pageResponse), HttpStatus.OK);
    }
    @GetMapping("/getQuiz/{quizId}")
    public ResponseEntity<ApiResponse<Quiz>> getQuizById(@PathVariable Long quizId) throws QuizException {
        Quiz quiz=quizService.getQuizById(quizId);
        return ResponseEntity.ok(new ApiResponse<>(true,"Quiz fetched through quiz id successfully.",quiz));
    }
    @GetMapping("/calculateQuizScore")
    public ResponseEntity<ApiResponse<Integer>> calculateQuizScore(@RequestBody List<Response> responseList) throws QuizException {
        int marks=quizService.calculateQuizScore(responseList);
        return ResponseEntity.ok(new ApiResponse<>(true,"Quiz marks for the provided response is"+marks,marks));
    }
    @PostMapping("/createQuiz")
    public ResponseEntity<ApiResponse<Long>> createQuiz(@RequestBody Quiz quiz){
        Long quizId= quizService.createQuiz(quiz);
        return new ResponseEntity<>(new ApiResponse<>(true,"Quiz created successfully",quizId), HttpStatus.CREATED);
    }
    @PutMapping("/addExistingQuestionToQuiz")
    public ResponseEntity<ApiResponse<String>> addExistingQuestionToQuiz(@RequestParam Long quizId,@RequestBody Set<Long> qstIdList) throws QuizException {
        String msg=quizService.addExistingQuestionToQuiz(quizId,qstIdList);
        return ResponseEntity.ok(new ApiResponse<>(true,"Added existing question to quiz",msg));
    }
    @PutMapping("/addNewQuestionToQuiz")
    public ResponseEntity<ApiResponse<String>> addNewQuestionToQuiz(@RequestParam Long quizId,@RequestBody Set<Question> qstList) throws QuizException {
        String msg=quizService.addNewQuestionToQuiz(quizId,qstList);
        return ResponseEntity.ok(new ApiResponse<>(true,"Added new question to quiz",msg));
    }
    @DeleteMapping("/deleteQuestionFromQuiz")
    public ResponseEntity<ApiResponse<String>> deleteQuestionFromQuiz(@RequestParam Long quizId,@RequestBody Set<Long> qstIdList) throws QuizException{
        String msg=quizService.deleteQuestionFromQuiz(quizId,qstIdList);
        return ResponseEntity.ok(new ApiResponse<>(true,"Question got removed successfully from the quiz.",msg));
    }
    @DeleteMapping("/deleteQuiz/{quizId}")
    public ResponseEntity<ApiResponse<String>> deleteQuizFromId(@PathVariable Long quizId){
        String msg= quizService.deleteQuizFromId(quizId);
        return ResponseEntity.ok(new ApiResponse<>(true,"Quiz got deleted successfully",msg));
    }
}
