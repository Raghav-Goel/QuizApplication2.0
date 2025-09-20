package com.quiz.quizApplication.service;

import com.quiz.quizApplication.entity.Quiz;
import com.quiz.quizApplication.entity.UserQuiz;
import com.quiz.quizApplication.exception.QuizException;

import java.util.List;
import java.util.Optional;

public interface UserQuizService {
    public List<UserQuiz> getUserAllQuizDetails(Long userId) throws QuizException;
    public int findMaxAttemptNumberByUserAndQuiz(Long userId,Long quizId);
    public UserQuiz getUserSpecificQuizDetails(Long userId,Long quizId) throws QuizException;
    void updateMarks(Long userId, Long quizId, int marks) throws QuizException;
}
