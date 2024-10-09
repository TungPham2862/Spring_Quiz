package com.example.springquiz.controller;

import com.example.springquiz.model.dto.AnswerDTO;
import com.example.springquiz.service.impl.AnswerService;
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
@RequestMapping("/answers")
public class AnswerController {

    AnswerService answerService;

    @PostMapping
    public ResponseEntity<?> createAnswer(@Valid @RequestBody AnswerDTO dto, UriComponentsBuilder uriComponentsBuilder) {
        int answerId = answerService.createNewAnswer(dto);
        UriComponents uriComponents = uriComponentsBuilder
                .path("/answers/{id}")
                .buildAndExpand(answerId);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllAnswers() {
        return ResponseEntity.ok(answerService.getAllAnswers());
    }

    @GetMapping("/question/{questionId}")
    public ResponseEntity<?> findAnswersByQuestionId(@PathVariable int questionId) {
        return ResponseEntity.ok(answerService.getAnswersByQuestionId(questionId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findAnswerById(@PathVariable int id) {
        return ResponseEntity.ok(answerService.getAnswerById(id));
    }

//    @GetMapping("/{question_id}")
//    public ResponseEntity<?> findAnswersByQuestionId(@PathVariable int question_id) {
//        return ResponseEntity.ok(answerService.getAllAnswersByQuestionId(question_id));
//    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAnswerById(@PathVariable int id, @Valid @RequestBody AnswerDTO dto) {
        answerService.updateAnswer(id, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnswerById(@PathVariable int id) {
        answerService.deleteAnswerById(id);
        return ResponseEntity.ok().build();
    }
}
