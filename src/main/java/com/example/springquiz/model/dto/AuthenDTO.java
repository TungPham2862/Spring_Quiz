package com.example.springquiz.model.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthenDTO {
    @NotEmpty
    private String token;
    @NotEmpty
    private boolean authenticated;
}
