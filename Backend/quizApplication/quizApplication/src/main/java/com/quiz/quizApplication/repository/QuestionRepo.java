package com.quiz.quizApplication.repository;

import com.quiz.quizApplication.entity.Question;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface QuestionRepo extends JpaRepository<Question,Long> {
    public Optional<Question> getQuestionByQuestionId(Long id);
    @Query("Select q from Question q where q.questionText like %:desp%")
    public Optional<Question> getQuestionByDescription(@Param("desp") String desp);
}
