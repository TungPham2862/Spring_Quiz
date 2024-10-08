package com.example.springquiz.service.impl;

import com.example.springquiz.builder.QuestionBuilder;
import com.example.springquiz.exception.CustomizedNotFoundException;
import com.example.springquiz.model.domain.Question;
import com.example.springquiz.model.dto.QuestionDTO;
import com.example.springquiz.repository.IQuestionRepository;
import com.example.springquiz.service.IQuestionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class QuestionService implements IQuestionService {
    private final IQuestionRepository questionRepository;
    private final QuestionBuilder questionBuilder;


    @Override
    public Long createNewQuestion(QuestionDTO dto) {
        return Stream.of(dto)
                .map(questionBuilder::build)
                .map(questionRepository::save)
                .map(Question::getQuestion_id)
                .findFirst()
                .get();
    }

    @Override
    public List<Optional<QuestionDTO>> getAllQuestions() {
        return questionRepository.findAll()
                .stream()
                .map(questionBuilder::build)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<QuestionDTO> getQuestionById(Long id) {
        return questionRepository.findById(id)
                .map(questionBuilder::build)
                .orElseThrow(() -> new CustomizedNotFoundException(String.format("No such Question for id '%s'", id)));
    }

    @Override
    public Optional<QuestionDTO> updateQuestion(Long id, QuestionDTO dto) {
        return questionRepository.findById(id)
                .map(model -> questionBuilder.build(dto, model))
                .map(questionRepository::save)
                .map(questionBuilder::build)
                .orElseThrow(() -> new CustomizedNotFoundException(String.format("No such Question for id '%s'", id)));

    }

    @Override
    public void deleteQuestionById(Long id) {
        questionRepository.deleteById(id);
    }

    @Override
    public void populate() {

    }
}
