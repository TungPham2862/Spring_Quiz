package com.example.springquiz.service;

import com.example.springquiz.model.dto.QuestionDTO;

import java.util.List;
import java.util.Optional;

public interface IQuestionService {
    int createNewQuestion(QuestionDTO dto);

    List<Optional<QuestionDTO>> getAllQuestions();

    Optional<QuestionDTO> getQuestionById(int id);

    Optional<QuestionDTO> updateQuestion(int id, QuestionDTO dto);

    void deleteQuestionById(int id);

    void populate();
}
