package com.example.springquiz.service;

import com.example.springquiz.model.dto.AnswerDTO;
import com.example.springquiz.model.dto.QuestionDTO;

import java.util.List;
import java.util.Optional;

public interface IQuestionService {
    Long createNewQuestion(QuestionDTO dto);

    List<Optional<QuestionDTO>> getAllQuestions();

    Optional<QuestionDTO> getQuestionById(Long id);

    Optional<QuestionDTO> updateQuestion(Long id, QuestionDTO dto);

    void deleteQuestionById(Long id);

    void populate();
}
