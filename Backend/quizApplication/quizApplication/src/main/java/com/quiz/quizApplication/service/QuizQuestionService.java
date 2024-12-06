package com.quiz.quizApplication.service;

public interface QuizQuestionService {
    public boolean checkIfQuestionPresentInQuiz(Long quizId,Long qstId);
    public boolean isQuestionPresentInAnyQuiz(Long questionId);
}
