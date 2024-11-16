package com.quiz.quizApplication.service;

import com.quiz.quizApplication.entity.Options;
import com.quiz.quizApplication.entity.Question;
import com.quiz.quizApplication.exception.QuizException;
import org.springframework.data.jpa.repository.Query;

public interface QuestionService {
    public Question getQuestionById(Long id) throws QuizException;

    public Question getQuestionByDescription(String desp) throws QuizException;
    public Long addQuestion(Question question);
    public String updateQuestion(Question question);
    public String addOptionForQuestion(Long qstId, Options options) throws QuizException;

    public String deleteQuestionById(Long qstId);
    public String deleteOptionFromQuestion(Long qstId,Long optId) throws QuizException;
}
