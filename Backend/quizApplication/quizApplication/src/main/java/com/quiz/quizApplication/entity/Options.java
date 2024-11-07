package com.quiz.quizApplication.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Options {
    @Id
    Long optionId;
    String optionText;
}
