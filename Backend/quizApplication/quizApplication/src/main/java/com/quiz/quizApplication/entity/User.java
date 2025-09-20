package com.quiz.quizApplication.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Data
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long userId;
    @NotNull
    @Pattern(regexp="([A-Za-z]+)( [A-Za-z]+)*", message="Name should contain only alphabets and space")
    String name;
    String emailId;
    String password;
    String phoneNumber;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    List<Quiz> createdQuizzes=new ArrayList<>();
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="user_quiz" , joinColumns = @JoinColumn(name="user_id"),inverseJoinColumns = @JoinColumn(name="quiz_id"))
    Set<Quiz> attemptedQuizzes=new HashSet<>();
}
