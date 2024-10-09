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
    private int quiz_id;
    private String quiz_name;
    private String description;
    private String quiz_type;
    private Date date;
    private Timestamp duration;
    private String status;


    @ManyToMany
    @JoinTable(
            name="quiz_question",
            joinColumns = @JoinColumn(name="quiz_id"),
            inverseJoinColumns = @JoinColumn(name="question_id")
    )
    private Set<Question> questions;

    @Builder
    public Quiz(String status, Timestamp duration, Date date, String quiz_type, String description, String quiz_name, int quiz_id) {
        this.status = status;
        this.duration = duration;
        this.date = date;
        this.quiz_type = quiz_type;
        this.description = description;
        this.quiz_name = quiz_name;
        this.quiz_id = quiz_id;
    }
}
