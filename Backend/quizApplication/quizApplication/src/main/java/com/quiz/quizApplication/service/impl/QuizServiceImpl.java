package com.quiz.quizApplication.service.impl;

import com.quiz.quizApplication.entity.Question;
import com.quiz.quizApplication.entity.Quiz;
import com.quiz.quizApplication.exception.QuizException;
import com.quiz.quizApplication.repository.QuizRepo;
import com.quiz.quizApplication.service.QuestionService;
import com.quiz.quizApplication.service.QuizService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class QuizServiceImpl implements QuizService {

    @Autowired
    QuizRepo quizRepo;
    @Autowired
    QuestionService questionService;
    @Override
    public Quiz getQuizById(Long id) throws QuizException {
        Optional<Quiz> optionalQuiz=quizRepo.findById(id);
        return optionalQuiz.orElseThrow(()->new QuizException("There is no Quiz with quiz id: "+id));
    }

    @Override
    public Long createQuiz(Quiz quiz) {
//        quiz.setQuestionList(questionList);
        quizRepo.save(quiz);
        return  quiz.getQuizId();
    }

    @Override
    public String addQuestionToQuiz(Long quizId, List<Long> qstIdList) throws QuizException {
        Quiz quiz=getQuizById(quizId);
        for(Long qstId:qstIdList){
            Question question= questionService.getQuestionById(qstId);
            quiz.getQuestionList().add(question);
        }
        return "Added question successfully.";
    }
}
