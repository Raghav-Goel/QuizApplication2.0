package com.quiz.quizApplication.service;

import com.quiz.quizApplication.entity.Options;
import com.quiz.quizApplication.exception.QuizException;

public interface OptionService {
    public Options getOptionsById(Long id) throws QuizException;
    public Long addOption(Options option);
    public String updateOption(Long id,String desp) throws QuizException;
    public String deleteOption(Long id);
}
