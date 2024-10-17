package com.example.springquiz.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizDTO {

    @NotEmpty
    private String quizName;

    private String description;

    @NotEmpty
    private String quizType;

    private Date date;

    private int duration;

    @NotEmpty
    private String status;

    private Integer accountId;

    private Set<QuestionDTO> questions;

}
