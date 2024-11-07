package com.quiz.quizApplication.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class UserQuiz {
    @Id
    Long userQuizId;
    Integer marks;
    boolean attempted;
}
