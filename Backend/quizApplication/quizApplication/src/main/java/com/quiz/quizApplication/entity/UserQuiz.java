package com.quiz.quizApplication.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class UserQuiz {
    @Id
    Long userQuizId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    Quiz quiz;

    Integer marks;
    boolean attempted;
}
