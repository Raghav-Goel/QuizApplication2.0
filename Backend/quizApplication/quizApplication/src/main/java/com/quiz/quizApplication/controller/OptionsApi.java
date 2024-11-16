package com.quiz.quizApplication.controller;

import com.quiz.quizApplication.entity.Options;
import com.quiz.quizApplication.exception.QuizException;
import com.quiz.quizApplication.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Options")
public class OptionsApi {
    @Autowired
    OptionService optionService;

    @GetMapping("/getOptionById")
    public ResponseEntity<ApiResponse<Options>> getOptionById(Long optionId) throws QuizException {
        Options options=optionService.getOptionsById(optionId);
        return new ResponseEntity<>(new ApiResponse<>(true,"Got option by id successfully.",options), HttpStatus.OK);
    }
    @PostMapping("/addOption")
    public ResponseEntity<ApiResponse<Long>> addOption(Options options){
        Long optId=optionService.addOption(options);
        return new ResponseEntity<>(new ApiResponse<>(true,"Created new Option successfully",optId),HttpStatus.CREATED);
    }
    @PutMapping("/updateOption/{id}")
    public ResponseEntity<ApiResponse<String>> updateOption(@PathVariable Long optId,@RequestParam String desp) throws QuizException {
        String msg= optionService.updateOption(optId,desp);
        return new ResponseEntity<>(new ApiResponse<>(true,"Update Successful",msg),HttpStatus.OK);
    }

    @DeleteMapping("deleteOption/{optId}")
    public ResponseEntity<ApiResponse<String>> deleteOption(@PathVariable Long optId){
        String msg= optionService.deleteOption(optId);
        return new ResponseEntity<>(new ApiResponse<>(true,"Deleted option successfully",msg),HttpStatus.OK);
    }

}
