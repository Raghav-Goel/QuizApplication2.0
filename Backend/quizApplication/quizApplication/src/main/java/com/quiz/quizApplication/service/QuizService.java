package com.quiz.quizApplication.service;

import com.quiz.quizApplication.entity.Question;
import com.quiz.quizApplication.entity.Quiz;
import com.quiz.quizApplication.exception.QuizException;

import java.util.List;

public interface QuizService {
    public Quiz getQuizById(Long id) throws QuizException;
    public Long createQuiz(Quiz quiz);

    String addQuestionToQuiz(Long quizId, List<Long> qstIdList) throws QuizException;
}
