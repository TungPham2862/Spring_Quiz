package com.example.springquiz.model.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@Table(name = "quiz")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int quizId;
    private String quizName;
    private String description;
    private String quizType;
    private Date date;
    private Timestamp duration;
    private String status;


    @ManyToMany
    @JoinTable(
            name="quiz_question",
            joinColumns = @JoinColumn(name="quizId"),
            inverseJoinColumns = @JoinColumn(name="questionId")
    )
    private Set<Question> questions;

    @Builder
    public Quiz(String status, Timestamp duration, Date date, String quizType, String description, String quizName, int quizId) {
        this.status = status;
        this.duration = duration;
        this.date = date;
        this.quizType = quizType;
        this.description = description;
        this.quizName = quizName;
        this.quizId = quizId;
    }
}
