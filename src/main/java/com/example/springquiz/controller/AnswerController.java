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
@RequestMapping("/answer")
public class AnswerController {

    AnswerService answerService;

    @PostMapping
    public ResponseEntity<?> createAnswer(@Valid @RequestBody AnswerDTO dto, UriComponentsBuilder uriComponentsBuilder) {
        Long answer_id = answerService.createNewAnswer(dto);
        UriComponents uriComponents = uriComponentsBuilder
                .path("/answer/{id}")
                .buildAndExpand(answer_id);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllAnswers() {
        return ResponseEntity.ok(answerService.getAllAnswers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findAnswerById(@PathVariable Long id) {
        return ResponseEntity.ok(answerService.getAnswerById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAnswerById(@PathVariable Long id, @Valid @RequestBody AnswerDTO dto) {
        answerService.updateAnswer(id, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnswerById(@PathVariable Long id) {
        answerService.deleteAnswerById(id);
        return ResponseEntity.ok().build();
    }
}
