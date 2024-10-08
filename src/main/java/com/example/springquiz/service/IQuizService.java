package com.example.springquiz.service;

import com.example.springquiz.model.dto.AnswerDTO;
import com.example.springquiz.model.dto.QuestionDTO;
import com.example.springquiz.model.dto.QuizDTO;

import java.util.List;
import java.util.Optional;

public interface IQuizService {
    Long createNewQuiz(QuizDTO dto);

    List<Optional<QuizDTO>> getAllQuizzes();

    Optional<QuizDTO> getQuizById(Long id);

    Optional<QuizDTO> updateQuiz(Long id, QuizDTO dto);

    void deleteQuizById(Long id);

    void populate();
}
