package com.example.springquiz.builder;

import com.example.springquiz.model.domain.Question;
import com.example.springquiz.model.domain.Quiz;
import com.example.springquiz.model.dto.QuestionDTO;
import com.example.springquiz.model.dto.QuizDTO;
import com.example.springquiz.repository.IQuestionRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class QuizBuilder {

    private final IQuestionRepository questionRepository;
    private final ModelMapper modelMapper;

    public Quiz build(QuizDTO dto) {
        Quiz model = modelMapper.map(dto, Quiz.class);
        return model;
    }

    public Optional<QuizDTO> build(Quiz domain) {
        Set<QuestionDTO> questions = questionRepository.findAllByQuizzes_QuizId(domain.getQuizId())
                .stream().map(question -> modelMapper.map(question, QuestionDTO.class))
                .collect(Collectors.toSet());
        QuizDTO dto = modelMapper.map(domain, QuizDTO.class);
        dto.setQuestions(questions);
        return Optional.of(dto);
    }

    public Quiz build(QuizDTO dto, Quiz domain) {
        Set<Question> questions = questionRepository.findAllByQuizzes_QuizId(domain.getQuizId())
                .stream().map(question -> modelMapper.map(question, Question.class))
                .collect(Collectors.toSet());
        domain.setQuestions(questions);
        modelMapper.map(dto, domain);
        return domain;
    }
}
