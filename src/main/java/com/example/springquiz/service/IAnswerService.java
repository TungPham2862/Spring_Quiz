package com.example.springquiz.service;

import com.example.springquiz.model.dto.AnswerDTO;

import java.util.List;
import java.util.Optional;

public interface IAnswerService {
    int createNewAnswer(AnswerDTO dto);

    List<Optional<AnswerDTO>> getAllAnswers();

    Optional<AnswerDTO> getAnswerById(int id);

    List<Optional<AnswerDTO>> getAnswersByQuestionId(int questionId);

    Optional<AnswerDTO> updateAnswer(int id, AnswerDTO dto);

    void deleteAnswerById(int id);

    void populate();
}

