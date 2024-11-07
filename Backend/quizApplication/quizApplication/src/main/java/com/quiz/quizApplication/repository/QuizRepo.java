package com.quiz.quizApplication.repository;

import com.quiz.quizApplication.entity.Quiz;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepo extends CrudRepository<Quiz,Long> {
}
