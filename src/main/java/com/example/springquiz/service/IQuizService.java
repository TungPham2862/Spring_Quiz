package com.example.springquiz.service;


import com.example.springquiz.model.dto.QuizDTO;

import java.util.List;
import java.util.Optional;

public interface IQuizService {
    int createNewQuiz(QuizDTO dto);

    List<Optional<QuizDTO>> getAllQuizzes();

    Optional<QuizDTO> getQuizById(int id);

    Optional<QuizDTO> updateQuiz(int id, QuizDTO dto);

    void deleteQuizById(int id);

    void populate();
}
