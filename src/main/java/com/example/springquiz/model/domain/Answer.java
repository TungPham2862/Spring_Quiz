package com.example.springquiz.model.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answer_id;
    private String answer;
    private String status;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
}
