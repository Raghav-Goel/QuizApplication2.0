package com.quiz.quizApplication.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class QuizQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long quizQuestionId;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    Quiz quiz;

    @ManyToOne
    @JoinColumn(name = "question_id")
    Question question;
}
