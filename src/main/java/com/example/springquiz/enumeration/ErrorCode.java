package com.example.springquiz.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ErrorCode {

    USER_EXISTED(1001, "User already existed", HttpStatus.BAD_REQUEST),

    USER_NOT_FOUND(1002, "User not found", HttpStatus.BAD_REQUEST),
    QUESTION_NOT_FOUND(1003, "Question not found", HttpStatus.NOT_FOUND),
    QUIZ_NOT_FOUND(1004, "Quiz not found", HttpStatus.NOT_FOUND),
    ANSWER_NOT_FOUND(1005, "Answer not found", HttpStatus.NOT_FOUND),
    ROLE_NOT_FOUND(1002, "Role not found", HttpStatus.NOT_FOUND),

    UNAUTHENTICATED(1010, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1011, "Unauthorized", HttpStatus.FORBIDDEN),

    INVALID_USERNAME(1011,"Invalid username", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(1012,"Invalid password", HttpStatus.BAD_REQUEST),
    INVALID_FULLNAME(1013,"Invalid full name", HttpStatus.BAD_REQUEST),
    INVALID_EMAIL(1014,"Invalid email address", HttpStatus.BAD_REQUEST),
    INVALID_PHONE_NUMBER(1015,"Invalid phone number", HttpStatus.BAD_REQUEST),

    UNCATEGORIZED_EX(1999,"Uncategorized exception!",HttpStatus.INTERNAL_SERVER_ERROR);

    private int code;
    private String message;
    private HttpStatusCode httpStatusCode;
}
