package com.example.springquiz.repository;

import com.example.springquiz.model.domain.Answer;
import com.example.springquiz.model.domain.Question;
import com.example.springquiz.model.domain.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IQuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findAllByQuizzes_QuizId(Integer quizId);
}
