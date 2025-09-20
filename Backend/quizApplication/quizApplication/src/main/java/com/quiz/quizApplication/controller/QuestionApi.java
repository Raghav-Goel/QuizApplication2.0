package com.quiz.quizApplication.controller;

import com.quiz.quizApplication.Utility.PageResponse;
import com.quiz.quizApplication.Utility.Response;
import com.quiz.quizApplication.entity.Options;
import com.quiz.quizApplication.entity.Question;
import com.quiz.quizApplication.entity.User;
import com.quiz.quizApplication.exception.QuizException;
import com.quiz.quizApplication.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/question")
public class QuestionApi {
    @Autowired
    QuestionService questionService;

    @GetMapping("/getAllQuestions")
    public ResponseEntity<ApiResponse<PageResponse<Question>>> getAllQuestions(
            @RequestParam(value = "pageNumber",defaultValue = "0",required = false) int pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "5",required = false) int pageSize
    ){
        PageResponse<Question> pageResponse=questionService.getAllQuestions(pageNumber,pageSize);
        return new ResponseEntity<>(new ApiResponse<>(true,"Getting all questions",pageResponse), HttpStatus.OK);
    }

    @GetMapping("/getQuestionDetails/{qstId}")
    public ResponseEntity<ApiResponse<Question>> getQuestionById(@PathVariable Long qstId) throws QuizException {
        Question question=questionService.getQuestionById(qstId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Question found", question));
    }
    @GetMapping("/getQuestionsFromDescription")
    public ResponseEntity<ApiResponse<List<Question>>> getQuestionsFromDescription(@RequestParam String desp) throws QuizException {
        List<Question> question=questionService.getQuestionsByDescription(desp);
//        return ResponseEntity.ok(new ApiResponse<>(true, "Question found", question));
        return new ResponseEntity<>(new ApiResponse<>(true, "List of Questions that contains: "+desp, question),HttpStatus.OK);
    }

    @PostMapping("/addQuestion")
    public ResponseEntity<ApiResponse<Long>> addQuestion(@RequestBody Question question){
        Long questionId= questionService.addQuestion(question);
        return ResponseEntity.ok(new ApiResponse<>(true,"Added question successfully",questionId));
    }
    @PutMapping("/updateQuestionWithNewOption")
    public ResponseEntity<ApiResponse<String>> addOptionsForQuestion(@RequestParam Long qstId, @RequestBody Set<Options> optionsList) throws QuizException {
        questionService.addOptionForQuestion(qstId,optionsList);
        return ResponseEntity.ok(new ApiResponse<>(true,"Successfully updated the option of the question","Option Updated"));
    }
    @PutMapping("/updateQuestionWithExistingOption")
    public ResponseEntity<ApiResponse<String>> addExistingOptionsForQuestion(@RequestParam Long qstId, @RequestBody Set<Long> optionsList) throws QuizException {
        String msg=questionService.addExistingOptionForQuestion(qstId,optionsList);
        return ResponseEntity.ok(new ApiResponse<>(true,"Successfully updated the existing option of the question",msg));
    }
    @PutMapping("/updateQuestionCorrectAns")
    public ResponseEntity<ApiResponse<String>> updateQuestionCorrectAns(@RequestParam Long qstId,@RequestParam String correctAns) throws QuizException {
        String msg=questionService.updateQuestionCorrectAns(qstId,correctAns);
        return  ResponseEntity.ok(new ApiResponse<>(true,"Updated the question description successfully",msg));
    }
    @PutMapping("/updateQuestionDescription")
    public ResponseEntity<ApiResponse<String>> updateQuestionDescription(@RequestParam Long qstId,@RequestParam String qstDescp) throws QuizException {
        String msg=questionService.updateQuestionDescription(qstId,qstDescp);
        return  ResponseEntity.ok(new ApiResponse<>(true,"Updated the question description successfully",msg));
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
