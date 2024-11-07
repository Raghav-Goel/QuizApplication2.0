package com.quiz.quizApplication.service;

import com.quiz.quizApplication.entity.Quiz;
import com.quiz.quizApplication.entity.User;
import com.quiz.quizApplication.entity.UserQuiz;
import com.quiz.quizApplication.exception.QuizException;

import java.util.List;

public interface UserService {
    public User getUserById(Long userId) throws QuizException;
    public User getUserByEmail(String emailId) throws QuizException;
    public Long addUser(User user) throws QuizException;
    public User addQuizDetails(Long userId,Quiz quiz);
    public String updateEmailId(Long userId,String newEmailId)  throws QuizException;
    public String updatePassword(Long userId,String password);
    public List<UserQuiz> getUserQuizDetails(Long userId);
}
