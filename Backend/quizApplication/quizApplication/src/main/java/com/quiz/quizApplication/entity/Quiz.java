package com.quiz.quizApplication.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long quizId;
    String title;
    String level;
    LocalTime quizTime;
    @ManyToMany(cascade= CascadeType.ALL)
    @JoinTable(name="quiz_question", joinColumns = @JoinColumn(name="quiz_id"), inverseJoinColumns = @JoinColumn(name="question_id"))
    Set<Question> questionList=new HashSet<>();
}
