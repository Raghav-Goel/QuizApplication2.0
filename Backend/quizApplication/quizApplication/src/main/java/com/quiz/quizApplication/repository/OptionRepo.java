package com.quiz.quizApplication.repository;

import com.quiz.quizApplication.entity.Options;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OptionRepo extends CrudRepository<Options,Long> {
    public Optional<Options> getOptionsByOptionId(Long Id);
}
