package com.quiz.quizApplication.service.impl;

import com.quiz.quizApplication.entity.Options;
import com.quiz.quizApplication.entity.Question;
import com.quiz.quizApplication.exception.QuizException;
import com.quiz.quizApplication.repository.QuestionRepo;
import com.quiz.quizApplication.service.OptionService;
import com.quiz.quizApplication.service.QuestionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    QuestionRepo questionRepo;
    @Autowired
    OptionService optionService;
    @Autowired
    Environment environment;
    @Override
    public Question getQuestionById(Long id) throws QuizException {
        Optional<Question> optionalQuestion=questionRepo.getQuestionByQuestionId(id);
        return optionalQuestion.orElseThrow(()->new QuizException(environment.getProperty("Service.QUESTION_NOT_FOUND")));
    }

    @Override
    public Question getQuestionByDescription(String desp) throws QuizException {
        Optional<Question> optionalQuestion=questionRepo.getQuestionByDescription(desp);
        return optionalQuestion.orElseThrow(()->new QuizException(environment.getProperty("Service.QUESTION_DESCRIPTION_NOT_FOUND")));
    }

    @Override
    public Long addQuestion(Question question) {
        System.out.println(question);
        questionRepo.save(question);
        return question.getQuestionId();
    }

    @Override
    public String updateQuestion(Question question) {
        return null;
    }

    @Override
    @Transactional
    public String addOptionForQuestion(Long qstId, Set<Options> optionsList) throws QuizException {
        //For now we are able to add duplicates as well, we needed to implement the login in which duplicates are not allowed.
        Question question=getQuestionById(qstId);
        for(Options options:optionsList){
            question.getOptionsList().add(options);
        }
        return "Added option to the existing question successfully.";
    }
    @Override
    public String addExistingOptionForQuestion(Long qstId, Set<Long> optionsList) throws QuizException {
        Question question=getQuestionById(qstId);
        for(Long optionId:optionsList){
            Options options=optionService.getOptionsById(optionId);
            question.getOptionsList().add(options);
        }
        return "Added existing option to the existing question successfully.";
    }

    @Override
    public String updateQuestionDescription(Long qstId, String qstDescp) throws QuizException {
        Question question=getQuestionById(qstId);
        question.setQuestionText(qstDescp);
        return "Updated question description";
    }

    @Override
    public String deleteQuestionById(Long qstId) {
        questionRepo.deleteById(qstId);
        return "Great Success";
    }
    @Override
    @Transactional
    public String deleteOptionFromQuestion(Long qstId,Long optId) throws QuizException {
        Question question=getQuestionById(qstId);
        Options options=optionService.getOptionsById(optId);
        question.getOptionsList().remove(options);
        optionService.deleteOption(optId);
        return "Option removed Successfully";
    }


}
