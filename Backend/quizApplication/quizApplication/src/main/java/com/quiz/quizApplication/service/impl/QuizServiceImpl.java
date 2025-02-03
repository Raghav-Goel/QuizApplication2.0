package com.quiz.quizApplication.service.impl;

import com.quiz.quizApplication.Utility.PageResponse;
import com.quiz.quizApplication.Utility.Response;
import com.quiz.quizApplication.entity.Question;
import com.quiz.quizApplication.entity.Quiz;
import com.quiz.quizApplication.exception.QuizException;
import com.quiz.quizApplication.repository.QuizRepo;
import com.quiz.quizApplication.service.QuestionService;
import com.quiz.quizApplication.service.QuizQuestionService;
import com.quiz.quizApplication.service.QuizService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class QuizServiceImpl implements QuizService {

    @Autowired
    QuizRepo quizRepo;
    @Autowired
    QuestionService questionService;
    @Autowired
    QuizQuestionService quizQuestionService;
    @Override
    public boolean isQuestionPresentInAnyQuiz(Long questionId) {
        //Checks if a specific question is present in any quiz.
        return quizQuestionService.isQuestionPresentInAnyQuiz(questionId);
    }
    @Override
    public PageResponse<Quiz> getAllQuiz(int pageNum, int pageSize) {
        PageRequest pageRequest= PageRequest.of(pageNum,pageSize);
        Page<Quiz> page=quizRepo.findAll(pageRequest);
        PageResponse<Quiz> pageResponse=new PageResponse<>();
        pageResponse.setList(page.getContent());
        pageResponse.setPageNumber(page.getNumber());
        pageResponse.setPageSize(page.getSize());
        pageResponse.setTotalPages(page.getTotalPages());
        pageResponse.setTotalElements(page.getTotalElements());
        pageResponse.setLast(page.isLast());
        return pageResponse;
    }
    @Override
    public Quiz getQuizById(Long id) throws QuizException {
        Optional<Quiz> optionalQuiz=quizRepo.findById(id);
        return optionalQuiz.orElseThrow(()->new QuizException("There is no Quiz with quiz id: "+id));
    }
    @Override
    public int calculateQuizScore(List<Response> responseList) throws QuizException {
        int marks=0;
        for(Response response:responseList){
            if(questionService.checkAnsForResponse(response))marks++;
        }
        return marks;
    }
    @Override
    public Long createQuiz(Quiz quiz) {
//        quiz.setQuestionList(questionList);
        quizRepo.save(quiz);
        return  quiz.getQuizId();
    }

    @Override
    public String addExistingQuestionToQuiz(Long quizId, Set<Long> qstIdList) throws QuizException {
        Quiz quiz=getQuizById(quizId);
        Set<Long> questionAlreadyPresentInQuiz=new HashSet<>();
        Set<Long> questionNotPresentInQuiz=new HashSet<>();
        for(Long qstId:qstIdList){
            boolean isQuestionInQuiz=quizQuestionService.checkIfQuestionPresentInQuiz(quizId,qstId);
            if(isQuestionInQuiz)questionAlreadyPresentInQuiz.add(qstId);
            else{
                Question question= questionService.getQuestionById(qstId);
                quiz.getQuestionList().add(question);
                questionNotPresentInQuiz.add(qstId);
            }
        }
        String msg="";
        if(!questionAlreadyPresentInQuiz.isEmpty())msg+=questionAlreadyPresentInQuiz+": These questions was already present in quiz.";
        if(!questionNotPresentInQuiz.isEmpty()){
            if(!msg.isEmpty())msg=msg+" and ";
            msg+=questionNotPresentInQuiz+": Added these questions in the list.";
        }
        return msg;
    }

    @Override
    public String addNewQuestionToQuiz(Long quizId, Set<Question> qstList) throws QuizException {
        Quiz quiz=getQuizById(quizId);
        for(Question question:qstList){
            quiz.getQuestionList().add(question);
        }
        return "Added new question successfully to quiz";
    }

    @Override
    public String deleteQuestionFromQuiz(Long quizId, Set<Long> qstIdList) throws QuizException {
        Quiz quiz=getQuizById(quizId);
        for(Long qstId:qstIdList){
            Question question=questionService.getQuestionById(qstId);
            quiz.getQuestionList().remove(question);
            if(!isQuestionPresentInAnyQuiz(qstId))questionService.deleteQuestionById(qstId);
        }

        return "Questions removed successfully from quiz";
    }

    @Override
    public String deleteQuizFromid(Long quizId) {
        quizRepo.deleteById(quizId);
        return "Quiz with quizId: "+quizId+" got deleted successfully";
    }

}
