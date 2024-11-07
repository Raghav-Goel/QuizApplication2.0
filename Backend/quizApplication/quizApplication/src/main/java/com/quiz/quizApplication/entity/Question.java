package com.quiz.quizApplication.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
public class Question {
    @Id
    Long questionId;
    String questionText;
    String correctAns;
    @ManyToMany(cascade= CascadeType.ALL)
    @JoinTable(name="question_options", joinColumns = @JoinColumn(name="question_id"), inverseJoinColumns = @JoinColumn(name="option_id"))
    List<Options> optionsList=new ArrayList<>();

}
