package com.example.springquiz.builder;

import com.example.springquiz.model.domain.Question;
import com.example.springquiz.model.domain.Quiz;
import com.example.springquiz.model.dto.QuestionDTO;
import com.example.springquiz.model.dto.QuizDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class QuestionBuilder {

    private final ModelMapper modelMapper;

    public Question build(QuestionDTO dto) {
        Question model = modelMapper.map(dto, Question.class);
        return model;
    }

    public Optional<QuestionDTO> build(Question domain) {
        QuestionDTO dto = modelMapper.map(domain, QuestionDTO.class);
        return Optional.of(dto);
    }

    public Question build(QuestionDTO dto, Question domain) {
        modelMapper.map(dto, domain);
        return domain;
    }
}
