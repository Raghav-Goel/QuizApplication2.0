package com.quiz.quizApplication.service.impl;

import com.quiz.quizApplication.entity.UserQuiz;
import com.quiz.quizApplication.exception.QuizException;
import com.quiz.quizApplication.repository.UserQuizRepo;
import com.quiz.quizApplication.service.UserQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserQuizServiceImpl implements UserQuizService {
    @Autowired
    UserQuizRepo userQuizRepo;
    @Override
    public List<UserQuiz> getUserAllQuizDetails(Long userId) throws QuizException {
        Optional<List<UserQuiz>> optionalList=userQuizRepo.getUserAllQuizDetails(userId);
        return optionalList.orElseThrow(()->new QuizException("Got exception during getUserAllQuizDetails"));
    }

    @Override
    public List<UserQuiz> getUserSpecificQuizDetails(Long userId, Long quizId) throws QuizException {
        Optional<List<UserQuiz>> optionalList=userQuizRepo.getUserSpecificQuizDetails(userId,quizId);
        return optionalList.orElseThrow(()->new QuizException("Got exception during getUserSpecificQuizDetails"));
    }
}
