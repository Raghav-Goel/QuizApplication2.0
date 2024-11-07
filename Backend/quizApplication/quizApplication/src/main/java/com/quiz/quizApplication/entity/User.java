package com.quiz.quizApplication.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Data
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long userId;
    @NotNull
    @Pattern(regexp="[A-Za-z]+( [A-Za-z]+)*", message="Name should contain only alphabets and space")
    String name;
    String emailId;
    String password;
    String phoneNumber;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    List<UserQuiz> userQuiz=new ArrayList<>();
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="user_quiz" , joinColumns = @JoinColumn(name="user_id"),inverseJoinColumns = @JoinColumn(name="quiz_id"))
    List<Quiz> submittedQuizzes=new ArrayList<>();
}
