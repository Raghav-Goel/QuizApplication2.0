package com.quiz.quizApplication.controller;

import com.quiz.quizApplication.Utility.PageResponse;
import com.quiz.quizApplication.Utility.Response;
import com.quiz.quizApplication.entity.Quiz;
import com.quiz.quizApplication.entity.User;
import com.quiz.quizApplication.exception.QuizException;
import com.quiz.quizApplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserApi {
    @Autowired
    UserService userService;
    @GetMapping("/getAllUsers")
    public ResponseEntity<ApiResponse<PageResponse<User>>> getAllUser(
            @RequestParam(value = "pageNumber",defaultValue = "0",required = false) int pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "5",required = false) int pageSize
    ){
        PageResponse<User> pageResponse=userService.getAllUsers(pageNumber,pageSize);
        return new ResponseEntity<>(new ApiResponse<>(true,"Getting all users",pageResponse), HttpStatus.OK);
    }
    @GetMapping("/getUser/{id}")
    public ResponseEntity<ApiResponse<User>> getUserById(@PathVariable Long id) throws QuizException {
        User user=userService.getUserById(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "User found", user));
    }
    @GetMapping("/emailId/{emailId}")
    public ResponseEntity<ApiResponse<User>> getUserByEmailId(@PathVariable String emailId) throws QuizException {
        User user=userService.getUserByEmail(emailId);
        return ResponseEntity.ok(new ApiResponse<>(true, "User found", user));
    }
    @GetMapping("/quizResult")
    public ResponseEntity<ApiResponse<Integer>> evaluateQuizResponse(@RequestParam Long userId, @RequestParam Long quizId,
                                                                  @RequestBody List<Response> responseList) throws QuizException {
        int marks=userService.evaluateQuizResponse(userId,quizId,responseList);
        return  ResponseEntity.ok(new ApiResponse<>(true,"User marks for quiz is: "+ marks,marks));
    }
    @PostMapping("/addUser")
    public ResponseEntity<ApiResponse<Long>> addUser(@RequestBody User user) throws QuizException {
        Long userId= userService.addUser(user);
        return ResponseEntity.ok(new ApiResponse<>(true, "User added", userId));
    }
    @PostMapping("/createQuiz/{userId}")
    public ResponseEntity<ApiResponse<String>> createQuiz(@RequestBody Quiz quiz, @PathVariable Long userId) throws QuizException {
        String msg= userService.createQuiz(quiz,userId);
        return ResponseEntity.ok(new ApiResponse<>(true, "User added", msg));
    }
    @PutMapping("/addQuiz")
    public ResponseEntity<ApiResponse<String>> addQuizDetails(@RequestParam Long userId,@RequestParam Long quizId)throws  QuizException{
        String msg=userService.addQuizDetails(userId,quizId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Quiz added successfully", msg));
    }
    //todo: The below method is still uncompleted.
    @PutMapping("/updateUser/{id}")
    public ResponseEntity<ApiResponse<String>> updateUser(@RequestBody User user,@PathVariable Long id)throws  QuizException{
        String msg=userService.updateUserDetails(user,id);
        return ResponseEntity.ok(new ApiResponse<>(true, "User updated", msg));
    }
}
