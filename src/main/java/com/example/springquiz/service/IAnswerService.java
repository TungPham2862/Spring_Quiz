package com.example.springquiz.service;

import com.example.springquiz.model.dto.AnswerDTO;

import java.util.List;
import java.util.Optional;

public interface IAnswerService {
    Long createNewAnswer(AnswerDTO dto);

    List<Optional<AnswerDTO>> getAllAnswers();

    Optional<AnswerDTO> getAnswerById(Long id);

    Optional<AnswerDTO> updateAnswer(Long id, AnswerDTO dto);

    void deleteAnswerById(Long id);

    void populate();
}

