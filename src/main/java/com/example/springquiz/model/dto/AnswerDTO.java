package com.example.springquiz.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDTO {

    @NotEmpty
    private String answer;

    @NotEmpty
    private String status;

    @NotNull
    private Integer questionId;


}
