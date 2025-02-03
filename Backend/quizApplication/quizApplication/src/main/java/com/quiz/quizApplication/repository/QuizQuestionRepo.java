package com.quiz.quizApplication.repository;

import com.quiz.quizApplication.entity.QuizQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizQuestionRepo extends JpaRepository<QuizQuestion,Long> {
    @Query("Select count(q) from QuizQuestion q where q.quiz.quizId=:quizId and q.question.questionId=:qstId")
    public Integer checkIfQuestionPresentInQuiz(@Param("quizId") Long quizId,@Param("qstId") Long qstId);

    /**
     * Checks if a specific question is present in any quiz.
     *
     * @param questionId the ID of the question to check
     * @return true if the question is used in any quiz, false otherwise
     */
    @Query("Select count(q)>0 from QuizQuestion q where q.question.questionId=:questionId")
    boolean isQuestionInAnyQuiz(@Param("questionId") Long questionId);
}
