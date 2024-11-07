package com.quiz.quizApplication.controller;

import com.quiz.quizApplication.entity.User;
import com.quiz.quizApplication.exception.QuizException;
import com.quiz.quizApplication.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class userApi {
    @Autowired
    UserService userService;
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<User>> getUserById(@PathVariable Long id) throws QuizException {
        User user=userService.getUserById(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "User found", user));
    }
    @GetMapping("/emailId/{emailId}")
    public ResponseEntity<ApiResponse<User>> getUserByEmailId(@PathVariable String emailId) throws QuizException {
        User user=userService.getUserByEmail(emailId);
        return ResponseEntity.ok(new ApiResponse<>(true, "User found", user));
    }
    @PostMapping("/addUser")
    public ResponseEntity<ApiResponse<Long>> addUser(@RequestBody User user) throws QuizException {
        Long userId= userService.addUser(user);
        return ResponseEntity.ok(new ApiResponse<>(true, "User added", userId));
    }
    @PutMapping("/updateUser/{id}")
    public ResponseEntity<ApiResponse<String>> updateUser(@RequestBody User user,@PathVariable Long id)throws  QuizException{

        return ResponseEntity.ok(new ApiResponse<>(true, "User updated", ""));
    }
}
