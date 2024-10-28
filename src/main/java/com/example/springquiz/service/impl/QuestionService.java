package com.example.springquiz.service.impl;

import com.example.springquiz.builder.QuestionBuilder;
import com.example.springquiz.enumeration.ErrorCode;
import com.example.springquiz.exception.CustomizedRuntimeException;
import com.example.springquiz.model.domain.Question;
import com.example.springquiz.model.dto.QuestionDTO;
import com.example.springquiz.repository.IQuestionRepository;
import com.example.springquiz.repository.IQuizRepository;
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
    private final IQuizRepository quizRepository;


    @Override
    public int createNewQuestion(QuestionDTO dto) {
        return Stream.of(dto)
                .map(questionBuilder::build)
                .map(questionRepository::save)
                .map(Question::getQuestionId)
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
    public List<Optional<QuestionDTO>> getAllQuestionsByQuizId(int quizId) {
        if (quizRepository.findById(quizId).isEmpty()) {
            throw new CustomizedRuntimeException(ErrorCode.QUIZ_NOT_FOUND);
        }
        return questionRepository.findAllByQuizzes_QuizId(quizId)
                .stream()
                .map(questionBuilder::build)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<QuestionDTO> getQuestionById(int id) {
        return questionRepository.findById(id)
                .map(questionBuilder::build)
                .orElseThrow(() -> new CustomizedRuntimeException(ErrorCode.QUESTION_NOT_FOUND));
    }

    @Override
    public Optional<QuestionDTO> updateQuestion(int id, QuestionDTO dto) {
        return questionRepository.findById(id)
                .map(model -> questionBuilder.build(dto, model))
                .map(questionRepository::save)
                .map(questionBuilder::build)
                .orElseThrow(() -> new CustomizedRuntimeException(ErrorCode.QUESTION_NOT_FOUND));

    }

    @Override
    public void deleteQuestionById(int id) {
        if (questionRepository.findById(id).isEmpty()) {
            throw new CustomizedRuntimeException(ErrorCode.QUESTION_NOT_FOUND);
        }
        questionRepository.deleteById(id);
    }

    @Override
    public void populate() {

    }
}
