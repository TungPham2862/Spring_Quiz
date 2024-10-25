package com.example.springquiz.enumeration;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ErrorCode {
    USER_EXISTED(1001, "User already existed"),
    USER_NOT_FOUND(1002, "User not found"),
    QUESTION_NOT_FOUND(1003, "Question not found"),
    QUIZ_NOT_FOUND(1004, "Quiz not found"),
    ANSWER_NOT_FOUND(1005, "Answer not found"),
    ROLE_NOT_FOUND(1002, "Role not found"),
    WRONG_CREDENTIALS(1010, "Wrong username or password");

    private int code;
    private String message;
}
