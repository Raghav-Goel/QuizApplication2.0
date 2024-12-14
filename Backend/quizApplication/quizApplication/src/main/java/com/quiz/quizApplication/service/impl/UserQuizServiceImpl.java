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
    public List<UserQuiz> getUserSpecificQuizDetails(Long userId, Long quizId) throws QuizException {
        Optional<List<UserQuiz>> optionalList=userQuizRepo.getUserSpecificQuizDetails(userId,quizId);
        return optionalList.orElseThrow(()->new QuizException("Got exception during getUserSpecificQuizDetails"));
    }

    @Override
    public void updateMarks(Long userId, Long quizId, int marks) throws QuizException {
        List<UserQuiz> userQuizList=getUserSpecificQuizDetails(userId,quizId);
        int currentAttempt = userQuizRepo.findMaxAttemptNumberByUserAndQuiz(userId, quizId)
                .orElse(0);
        if(currentAttempt==0){
            List<UserQuiz> list=getUserSpecificQuizDetails(userId,quizId);
            UserQuiz userQuiz=list.get(0);
            userQuiz.setMarks(marks);
            userQuiz.setAttemptNumber(1);
        }else{
            UserQuiz userQuiz=new UserQuiz();
            User user=userRepo.findById(userId).orElseThrow(() -> new QuizException(environment.getProperty("Service.USER_NOT_FOUND")));
            Quiz quiz=quizRepo.findById(quizId).orElseThrow(() -> new QuizException(environment.getProperty("Service.QUESTION_NOT_FOUND")));
            userQuiz.setUser(user);
            userQuiz.setQuiz(quiz);
            userQuiz.setMarks(marks);
            userQuiz.setAttemptNumber(currentAttempt+1);
            userQuizRepo.save(userQuiz);
        }
    }
}
