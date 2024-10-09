package com.example.springquiz.model.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDTO {

    @NotEmpty
    private String question;

    @NotEmpty
    private String questionType;

    @NotEmpty
    private String status;

}
