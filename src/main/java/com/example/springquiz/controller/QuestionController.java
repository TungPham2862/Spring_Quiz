package com.example.springquiz.controller;

import com.example.springquiz.model.dto.QuestionDTO;
import com.example.springquiz.service.impl.QuestionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@AllArgsConstructor
@RequestMapping("/questions")
public class QuestionController {

    QuestionService questionService;

    @PostMapping
    public ResponseEntity<?> createQuestion(@Valid @RequestBody QuestionDTO dto, UriComponentsBuilder uriComponentsBuilder) {
        int questionId = questionService.createNewQuestion(dto);
        UriComponents uriComponents = uriComponentsBuilder
                .path("/questions/{id}")
                .buildAndExpand(questionId);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllQuestions() {
        return ResponseEntity.ok(questionService.getAllQuestions());
    }

    @GetMapping("/quizs/{quizId}")
    public ResponseEntity<?> getQuestionsByQuizId(@PathVariable int quizId) {
        return ResponseEntity.ok(questionService.getAllQuestionsByQuizId(quizId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findQuestionById(@PathVariable int id) {
        return ResponseEntity.ok(questionService.getQuestionById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateQuestionById(@PathVariable int id, @Valid @RequestBody QuestionDTO dto) {
        questionService.updateQuestion(id, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQuestionById(@PathVariable int id) {
        questionService.deleteQuestionById(id);
        return ResponseEntity.ok().build();
    }
}
