package com.example.springquiz.builder;

import com.example.springquiz.model.domain.Quiz;
import com.example.springquiz.model.dto.QuizDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class QuizBuilder {

    private final ModelMapper modelMapper;
    public Quiz build(QuizDTO dto) {
        Quiz model = modelMapper.map(dto, Quiz.class);
        return model;
    }

    public Optional<QuizDTO> build(Quiz domain) {
        QuizDTO dto = modelMapper.map(domain, QuizDTO.class);
        return Optional.of(dto);
    }

    public Quiz build(QuizDTO dto, Quiz domain) {
        modelMapper.map(dto, domain);
        return domain;
    }
}
