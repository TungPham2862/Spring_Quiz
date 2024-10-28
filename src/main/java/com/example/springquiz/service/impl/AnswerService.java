package com.example.springquiz.service.impl;

import com.example.springquiz.builder.AnswerBuilder;
import com.example.springquiz.enumeration.ErrorCode;
import com.example.springquiz.exception.CustomizedRuntimeException;
import com.example.springquiz.model.domain.Answer;
import com.example.springquiz.model.dto.AnswerDTO;
import com.example.springquiz.repository.IAnswerRepository;
import com.example.springquiz.repository.IQuestionRepository;
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
    private final IQuestionRepository questionRepository;

    @Override
    public int createNewAnswer(AnswerDTO dto) {
        Answer answer = answerBuilder.build(dto);
        return answerRepository.save(answer).getAnswerId();
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
                .orElseThrow(() -> new CustomizedRuntimeException(ErrorCode.ANSWER_NOT_FOUND));
    }

    @Override
    public List<Optional<AnswerDTO>> getAnswersByQuestionId(int questionId) {
        if (questionRepository.findById(questionId).isEmpty()) {
            throw new CustomizedRuntimeException(ErrorCode.QUESTION_NOT_FOUND);
        }
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
                .orElseThrow(() -> new CustomizedRuntimeException(ErrorCode.ANSWER_NOT_FOUND));
    }

    @Override
    public void deleteAnswerById(int id) {
        if (answerRepository.findById(id).isEmpty()) {
            throw new CustomizedRuntimeException(ErrorCode.ANSWER_NOT_FOUND);
        }
        answerRepository.deleteById(id);
    }


    @Override
    public void populate() {

    }
}
