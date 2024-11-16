package com.quiz.quizApplication.service.impl;

import com.quiz.quizApplication.entity.Options;
import com.quiz.quizApplication.exception.QuizException;
import com.quiz.quizApplication.repository.OptionRepo;
import com.quiz.quizApplication.service.OptionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OptionServiceImpl implements OptionService {
    @Autowired
    OptionRepo optionRepo;
    @Override
    public Options getOptionsById(Long id) throws QuizException {
        Optional<Options> optionalOptions=optionRepo.getOptionsByOptionId(id);
        return optionalOptions.orElseThrow(()->new QuizException("Not able to find options with specific id: "+id));
    }

    @Override
    public Long addOption(Options option) {
        optionRepo.save(option);
        return option.getOptionId();
    }
    @Transactional
    @Override
    public String updateOption(Long id,String desp) throws QuizException {
        Options options=getOptionsById(id);
        options.setOptionText(desp);
        return "Option description is updated successfully";
    }
    @Override
    public String deleteOption(Long id){
        optionRepo.deleteById(id);
        return "Option with id: "+id+" is deleted successfully";
    }

}
