package com.quiz.quizApplication.repository;

import com.quiz.quizApplication.entity.UserQuiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserQuizRepo extends JpaRepository<UserQuiz,Long> {
    @Query("Select uq from UserQuiz uq where uq.user.userId=:userId")
    public Optional<List<UserQuiz>> getUserAllQuizDetails(@Param("userId") Long userId);
    @Query("Select uq from UserQuiz uq where uq.user.userId=:userId and uq.quiz.quizId=:quizId")
    public Optional<UserQuiz> getUserSpecificQuizDetails(@Param("userId") Long userId, @Param("quizId") Long quizId);
    @Query("SELECT MAX(uq.attemptNumber) FROM UserQuiz uq WHERE uq.user.userId=:userId and uq.quiz.quizId=:quizId")
    Optional<Integer> findMaxAttemptNumberByUserAndQuiz(@Param("userId") Long userId, @Param("quizId") Long quizId);

}
