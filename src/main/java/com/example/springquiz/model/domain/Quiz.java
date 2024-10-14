package com.example.springquiz.model.domain;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
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


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="quiz_question",
            joinColumns = @JoinColumn(name="quiz_id"),
            inverseJoinColumns = @JoinColumn(name="question_id")
    )
    private Set<Question> questions;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Builder
    public Quiz(int quizId, String quizName, String description, String quizType, Date date, Timestamp duration, String status) {
        this.quizId = quizId;
        this.quizName = quizName;
        this.description = description;
        this.quizType = quizType;
        this.date = date;
        this.duration = duration;
        this.status = status;
    }
}
