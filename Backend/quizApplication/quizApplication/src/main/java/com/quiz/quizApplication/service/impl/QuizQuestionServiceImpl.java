package com.quiz.quizApplication.service.impl;

import com.quiz.quizApplication.repository.QuizQuestionRepo;
import com.quiz.quizApplication.service.QuizQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuizQuestionServiceImpl implements QuizQuestionService {

    @Autowired
    QuizQuestionRepo quizQuestionRepo;
    @Override
    public boolean checkIfQuestionPresentInQuiz(Long quizId, Long qstId) {
        int count= quizQuestionRepo.checkIfQuestionPresentInQuiz(quizId,qstId);
        System.out.println(count);
        return count>0;
    }
    @Override
    public boolean isQuestionPresentInAnyQuiz(Long questionId) {
        //Checks if a specific question is present in any quiz.
        return quizQuestionRepo.isQuestionInAnyQuiz(questionId);
    }
}
