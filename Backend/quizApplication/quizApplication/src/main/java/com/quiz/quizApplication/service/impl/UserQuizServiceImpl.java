package com.quiz.quizApplication.service.impl;

import com.quiz.quizApplication.entity.Quiz;
import com.quiz.quizApplication.entity.User;
import com.quiz.quizApplication.entity.UserQuiz;
import com.quiz.quizApplication.exception.QuizException;
import com.quiz.quizApplication.repository.QuizRepo;
import com.quiz.quizApplication.repository.UserQuizRepo;
import com.quiz.quizApplication.repository.UserRepo;
import com.quiz.quizApplication.service.UserQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserQuizServiceImpl implements UserQuizService {
    @Autowired
    UserQuizRepo userQuizRepo;
    @Autowired
    QuizRepo quizRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    Environment environment;
    @Override
    public List<UserQuiz> getUserAllQuizDetails(Long userId) throws QuizException {
        Optional<List<UserQuiz>> optionalList=userQuizRepo.getUserAllQuizDetails(userId);
        return optionalList.orElseThrow(()->new QuizException("Got exception during getUserAllQuizDetails"));
    }

    @Override
    public UserQuiz getUserSpecificQuizDetails(Long userId, Long quizId) throws QuizException {
        Optional<UserQuiz> optional=userQuizRepo.getUserSpecificQuizDetails(userId,quizId);
        return optional.orElseThrow(()->new QuizException("Got exception during getUserSpecificQuizDetails"));
    }
    @Override
    public int findMaxAttemptNumberByUserAndQuiz(Long userId,Long quizId){
        return userQuizRepo.findMaxAttemptNumberByUserAndQuiz(userId, quizId)
                .orElse(0);
    }
    @Override
    public void updateMarks(Long userId, Long quizId, int marks) throws QuizException {
        UserQuiz userQuiz=getUserSpecificQuizDetails(userId,quizId);
        int currentAttempt = findMaxAttemptNumberByUserAndQuiz(userId, quizId);
        if(currentAttempt==0){
            userQuiz.setLatestMarks(marks);
            userQuiz.setBestMarks(marks);
            userQuiz.setAttemptNumber(1);
        }else{
            int prevBestMarks=userQuiz.getBestMarks();
            int bestMarks=Math.max(prevBestMarks,marks);
            userQuiz.setLatestMarks(marks);
            userQuiz.setBestMarks(bestMarks);
            userQuiz.setAttemptNumber(currentAttempt+1);
        }
    }
}
