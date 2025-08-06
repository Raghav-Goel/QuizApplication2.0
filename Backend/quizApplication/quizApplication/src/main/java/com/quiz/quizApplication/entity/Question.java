package com.quiz.quizApplication.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Data
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long questionId;
    String questionText;
    String correctAns;
    @ManyToMany(cascade= CascadeType.ALL)
    @JoinTable(name="question_options", joinColumns = @JoinColumn(name="question_id"), inverseJoinColumns = @JoinColumn(name="option_id"))
    Set<Options> optionsList=new HashSet<>();
}
