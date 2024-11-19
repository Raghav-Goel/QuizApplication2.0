package com.quiz.quizApplication.service;

import com.quiz.quizApplication.entity.Question;
import com.quiz.quizApplication.entity.Quiz;
import com.quiz.quizApplication.exception.QuizException;
import java.util.Set;

public interface QuizService {
    public Quiz getQuizById(Long id) throws QuizException;
    public Long createQuiz(Quiz quiz);
    String addExistingQuestionToQuiz(Long quizId, Set<Long> qstIdList) throws QuizException;
    String addNewQuestionToQuiz(Long quizId, Set<Question> qstList) throws QuizException;

    String deleteQuestionFromQuiz(Long quizId, Set<Long> qstIdList) throws QuizException;
}
