package com.example.springquiz.builder;

import com.example.springquiz.model.domain.Answer;
import com.example.springquiz.model.domain.Quiz;
import com.example.springquiz.model.dto.AnswerDTO;
import com.example.springquiz.model.dto.QuizDTO;
import com.example.springquiz.repository.IQuestionRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class AnswerBuilder {

    private final IQuestionRepository questionRepository;
    private final ModelMapper modelMapper;
    public Answer build(AnswerDTO dto) {
        Answer model = modelMapper.map(dto, Answer.class);
        questionRepository.findById(dto.getQuestionId())
                .ifPresent(model::setQuestion);
        return model;
    }

    public Optional<AnswerDTO> build(Answer domain) {
        AnswerDTO dto = modelMapper.map(domain, AnswerDTO.class);
        dto.setQuestionId(domain.getQuestion().getQuestionId());
        return Optional.of(dto);
    }

    public Answer build(AnswerDTO dto, Answer domain) {
        modelMapper.map(dto, domain);
        return domain;
    }
}
