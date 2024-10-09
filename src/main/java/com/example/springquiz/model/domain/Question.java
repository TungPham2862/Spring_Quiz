package com.example.springquiz.model.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int question_id;
    private String question;
    private String question_type;
    private String status;

    @ManyToMany(mappedBy = "questions", cascade = CascadeType.ALL)
    private Set<Quiz> quizzes;

    @OneToMany(mappedBy = "question")
    private Set<Answer> answers;
}
