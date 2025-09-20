package com.quiz.quizApplication.entity;

import com.quiz.quizApplication.entity.enumEntity.QuestionLevel;
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
    //In this I have recently added questionLevel, so needed to change it accordingly.
    //I have added the column in db.
    //Have to do changes in service class.
    @Enumerated(EnumType.STRING)
    QuestionLevel questionLevel;
    @ManyToMany(cascade= CascadeType.ALL)
    @JoinTable(name="question_options", joinColumns = @JoinColumn(name="question_id"), inverseJoinColumns = @JoinColumn(name="option_id"))
    Set<Options> optionsList=new HashSet<>();
}
