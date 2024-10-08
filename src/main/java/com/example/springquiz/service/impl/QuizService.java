package com.example.springquiz.service.impl;

import com.example.springquiz.builder.QuizBuilder;
import com.example.springquiz.exception.CustomizedNotFoundException;
import com.example.springquiz.model.domain.Quiz;
import com.example.springquiz.model.dto.AnswerDTO;
import com.example.springquiz.model.dto.QuizDTO;
import com.example.springquiz.repository.IQuizRepository;
import com.example.springquiz.service.IQuizService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class QuizService implements IQuizService {
    private final IQuizRepository quizRepository;
    private final QuizBuilder quizBuilder;

    @Override
    public Long createNewQuiz(QuizDTO dto) {
        return Stream.of(dto)
                .map(quizBuilder::build)
                .map(quizRepository::save)
                .map(Quiz::getQuiz_id)
                .findFirst()
                .get();
    }

    @Override
    public List<Optional<QuizDTO>> getAllQuizzes() {
        return quizRepository.findAll()
                .stream()
                .map(quizBuilder::build)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<QuizDTO> getQuizById(Long id) {
        return quizRepository.findById(id)
                .map(quizBuilder::build)
                .orElseThrow(() -> new CustomizedNotFoundException(String.format("Quiz with id %s not found", id)));
    }

    @Override
    public Optional<QuizDTO> updateQuiz(Long id, QuizDTO dto) {
        return quizRepository.findById(id)
                .map(model -> quizBuilder.build(dto,model))
                .map(quizRepository::save)
                .map(quizBuilder::build)
                .orElseThrow(()-> new CustomizedNotFoundException(String.format("Quiz with id %s not found", id)));
    }

    @Override
    public void deleteQuizById(Long id) {
        quizRepository.deleteById(id);
    }

    @Override
    public void populate() {

    }
}
