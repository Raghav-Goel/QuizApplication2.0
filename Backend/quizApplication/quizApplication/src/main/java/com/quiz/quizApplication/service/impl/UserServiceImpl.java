package com.quiz.quizApplication.service.impl;

import com.quiz.quizApplication.entity.Quiz;
import com.quiz.quizApplication.entity.User;
import com.quiz.quizApplication.entity.UserQuiz;
import com.quiz.quizApplication.exception.QuizException;
import com.quiz.quizApplication.repository.UserRepo;
import com.quiz.quizApplication.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    Environment environment;
    @Override
    public User getUserById(Long userId) throws QuizException {
        Optional<User> optionalUser=userRepo.findById(userId);
        return optionalUser.orElseThrow(()->new QuizException(environment.getProperty("Service.USER_EMAIL_NOT_FOUND")));
    }

    @Override
    public User getUserByEmail(String emailId) throws QuizException {
        if(emailId==null){
            throw new QuizException("Please provide the email Id");
        }
        Optional<User> optionalUser=userRepo.findByEmailId(emailId);
        return optionalUser.orElseThrow(()->new QuizException(environment.getProperty("Service.USER_EMAIL_NOT_FOUND")));
    }

    @Override
    public Long addUser(User newUser) throws QuizException {
        //In this first we have to check whether the provided emailId is already being used or not.
        if (userRepo.findByEmailId(newUser.getEmailId()).isPresent()) {
            throw new QuizException(environment.getProperty("Service.USER_ALREADY_EXIST"));
        }
        userRepo.save(newUser);
        return newUser.getUserId();
    }

    @Override
    public User addQuizDetails(Long userId,Quiz quiz) {
        return null;
    }

    @Override
    public String updateEmailId(Long userId, String newEmailId) throws QuizException{
        User user=userRepo.findById(userId).orElseThrow(()->new QuizException(environment.getProperty("Service.USER_EMAIL_NOT_FOUND")));
        user.setEmailId(newEmailId);
        return "EmailId is updated successfully.";
    }

    @Override
    public String updatePassword(Long userId, String password) {
        return null;
    }

    @Override
    public Set<UserQuiz> getUserQuizDetails(Long userId) {
        return null;
    }
}
