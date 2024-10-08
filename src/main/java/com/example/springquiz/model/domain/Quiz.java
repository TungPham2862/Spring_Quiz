package com.example.springquiz.model.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "quiz")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quiz_id;
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
}
