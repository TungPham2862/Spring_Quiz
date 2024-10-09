package com.example.springquiz.model.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AnswerDTO {

    @NotEmpty
    private String answer;

    @NotEmpty
    private String status;

    @NotEmpty
    private String question_id;
}
