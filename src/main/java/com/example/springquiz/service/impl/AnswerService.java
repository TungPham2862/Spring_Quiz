package com.example.springquiz.service.impl;

import com.example.springquiz.builder.AnswerBuilder;
import com.example.springquiz.exception.CustomizedNotFoundException;
import com.example.springquiz.model.domain.Answer;
import com.example.springquiz.model.dto.AnswerDTO;
import com.example.springquiz.repository.IAnswerRepository;
import com.example.springquiz.service.IAnswerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class AnswerService implements IAnswerService {
    private final IAnswerRepository answerRepository;
    private final AnswerBuilder answerBuilder;

    @Override
    public int createNewAnswer(AnswerDTO dto) {
        return Stream.of(dto)
                .map(answerBuilder::build)
                .map(answerRepository::save)
                .map(Answer::getAnswerId)
                .findFirst()
                .get();
    }

    @Override
    public List<Optional<AnswerDTO>> getAllAnswers() {
        return answerRepository.findAll()
                .stream()
                .map(answerBuilder::build)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AnswerDTO> getAnswerById(int id) {
        return answerRepository.findById(id)
                .map(answerBuilder::build)
                .orElseThrow(() -> new CustomizedNotFoundException(String.format("No such Answer for id '%s'", id)));
    }

    @Override
    public List<Optional<AnswerDTO>> getAnswersByQuestionId(int questionId) {
        return answerRepository.findByQuestion_QuestionId(questionId)
                .stream()
                .map(answerBuilder::build)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AnswerDTO> updateAnswer(int id, AnswerDTO dto) {
        return answerRepository.findById(id)
                .map(model -> answerBuilder.build(dto, model))
                .map(answerRepository::save)
                .map(answerBuilder::build)
                .orElseThrow(() -> new CustomizedNotFoundException(String.format("No such Answer for id '%s'", id)));
    }

    @Override
    public void deleteAnswerById(int id) {
        answerRepository.deleteById(id);
    }




    @Override
    public void populate() {

    }
}
