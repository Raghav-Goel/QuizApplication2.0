package com.quiz.quizApplication.service;

import com.quiz.quizApplication.Utility.PageResponse;
import com.quiz.quizApplication.Utility.Response;
import com.quiz.quizApplication.entity.Options;
import com.quiz.quizApplication.entity.Question;
import com.quiz.quizApplication.entity.User;
import com.quiz.quizApplication.exception.QuizException;

import java.util.List;
import java.util.Set;

public interface QuestionService {
    public PageResponse<Question> getAllQuestions(int pageNum, int pageSize);
    public Question getQuestionById(Long id) throws QuizException;
    public List<Question> getQuestionsByDescription(String desp) throws QuizException;
    public boolean checkAnsForResponse(Response response) throws QuizException;
    public Long addQuestion(Question question);
    public String addOptionForQuestion(Long qstId, Set<Options> optionsList) throws QuizException;
    public String addExistingOptionForQuestion(Long qstId, Set<Long> optionsList) throws QuizException;
    public String updateQuestionCorrectAns(Long questionId,String correctAns);
    public String updateQuestionDescription(Long qstId, String qstDescp) throws QuizException;
    public String deleteQuestionById(Long qstId);
    public String deleteOptionFromQuestion(Long qstId,Long optId) throws QuizException;
}
