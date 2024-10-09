package com.example.springquiz.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
public class QuizDTO {

    @NotEmpty
    @Size(min = 4, max = 30)
    private String quiz_name;

    private String description;

    @NotEmpty
    private String quiz_type;

    private Date date;

    private Time duration;

    @NotEmpty
    private String status;

    private Set<Integer> question_ids;

}
