package com.quiz.quizApplication.service.impl;

import com.quiz.quizApplication.Utility.PageResponse;
import com.quiz.quizApplication.Utility.Response;
import com.quiz.quizApplication.entity.Quiz;
import com.quiz.quizApplication.entity.User;
import com.quiz.quizApplication.entity.UserQuiz;
import com.quiz.quizApplication.exception.QuizException;
import com.quiz.quizApplication.repository.UserRepo;
import com.quiz.quizApplication.service.QuizService;
import com.quiz.quizApplication.service.UserQuizService;
import com.quiz.quizApplication.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    QuizService quizService;
    @Autowired
    Environment environment;
    @Autowired
    UserQuizService userQuizService;

    @Override
    public PageResponse<User> getAllUsers(int pageNum, int pageSize) {
        PageRequest pageRequest= PageRequest.of(pageNum,pageSize);
        Page<User> page=userRepo.findAll(pageRequest);
        PageResponse<User> pageResponse=new PageResponse<>();
        pageResponse.setList(page.getContent());
        pageResponse.setPageNumber(page.getNumber());
        pageResponse.setPageSize(page.getSize());
        pageResponse.setTotalPages(page.getTotalPages());
        pageResponse.setTotalElements(page.getTotalElements());
        pageResponse.setLast(page.isLast());
        return pageResponse;

    }

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
    public List<UserQuiz> getUserAllQuizDetails(Long userId) throws QuizException {
        return userQuizService.getUserAllQuizDetails(userId);
    }

    @Override
    public List<UserQuiz> getUserSpecificQuizDetails(Long userId, Long quizId) throws QuizException {
        return userQuizService.getUserSpecificQuizDetails(userId,quizId);
    }

    @Override
    public int evaluateQuizResponse(Long userId, Long quizId, List<Response> responseList) throws QuizException {
        int marks=quizService.calculateQuizScore(responseList);
        userQuizService.updateMarks(userId,quizId,marks);
        return marks;
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
    public String addQuizDetails(Long userId,Long quizId) throws QuizException {
        //todo: needed to add a check whether the quiz is already added to user or not.
        User user=getUserById(userId);
        Quiz quiz=quizService.getQuizById(quizId);
        user.getSubmittedQuizzes().add(quiz);
        return "Quiz has been added successfully to the user.";
    }

    @Override
    public String updateEmailId(Long userId, String newEmailId) throws QuizException{
        User user=userRepo.findById(userId).orElseThrow(()->new QuizException(environment.getProperty("Service.USER_NOT_FOUND")));
        user.setEmailId(newEmailId);
        return "EmailId is updated successfully.";
    }

    @Override
    public String updatePassword(Long userId, String password) {
        User user=userRepo.findById(userId).orElseThrow(()->new QuizException(environment.getProperty("Service.USER_NOT_FOUND")));
        user.setPassword(password);
        return null;
    }
    @Override
    public String updateUserDetails(User user,Long userId){
        User user1=userRepo.findById(userId).orElseThrow(()->new QuizException(environment.getProperty("Service.USER_NOT_FOUND")));
        user1.setPassword(user.getPassword());
        user1.setEmailId(user.getEmailId());
        user1.setPhoneNumber(user.getPhoneNumber());
        return "Updated the user details successfully.";
    }

}
