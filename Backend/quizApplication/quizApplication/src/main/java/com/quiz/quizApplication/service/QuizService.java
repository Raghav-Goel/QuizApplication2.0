package com.quiz.quizApplication.service;

import com.quiz.quizApplication.Utility.Response;
import com.quiz.quizApplication.entity.Question;
import com.quiz.quizApplication.entity.Quiz;
import com.quiz.quizApplication.exception.QuizException;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface QuizService {
    boolean isQuestionPresentInAnyQuiz(Long questionId);
    public Quiz getQuizById(Long id) throws QuizException;
    int calculateQuizScore(List<Response> responseList) throws QuizException;

    public Long createQuiz(Quiz quiz);
    String addExistingQuestionToQuiz(Long quizId, Set<Long> qstIdList) throws QuizException;
    String addNewQuestionToQuiz(Long quizId, Set<Question> qstList) throws QuizException;

    String deleteQuestionFromQuiz(Long quizId, Set<Long> qstIdList) throws QuizException;

    String deleteQuizFromid(Long quizId);
}
