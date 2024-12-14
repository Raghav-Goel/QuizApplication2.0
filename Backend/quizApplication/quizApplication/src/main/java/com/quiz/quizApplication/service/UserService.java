package com.quiz.quizApplication.service;

import com.quiz.quizApplication.Utility.Response;
import com.quiz.quizApplication.entity.Quiz;
import com.quiz.quizApplication.entity.User;
import com.quiz.quizApplication.entity.UserQuiz;
import com.quiz.quizApplication.exception.QuizException;

import java.util.List;
import java.util.Set;

public interface UserService {
    public User getUserById(Long userId) throws QuizException;
    public User getUserByEmail(String emailId) throws QuizException;
    public List<UserQuiz> getUserAllQuizDetails(Long userId) throws QuizException;
    public List<UserQuiz> getUserSpecificQuizDetails(Long userId,Long quizId) throws QuizException;
    public int evaluateQuizResponse(Long userId, Long quizId, List<Response> responseList) throws QuizException;

    public Long addUser(User user) throws QuizException;
    public String addQuizDetails(Long userId,Long quizId) throws QuizException;
    public String updateEmailId(Long userId,String newEmailId)  throws QuizException;
    public String updatePassword(Long userId,String password);

}
