package com.example.springquiz.repository;

import com.example.springquiz.model.domain.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IQuizRepository extends JpaRepository<Quiz, Integer> {
//    @Query("SELECT q FROM Quiz q LEFT JOIN FETCH q.questions WHERE q.quizId = :id")
//    Optional<Quiz> findByIdWithQuestions(@Param("id") int id);
//
//    @Query("SELECT q FROM Quiz q LEFT JOIN FETCH q.questions")
//    List<Quiz> findAllWithQuestions();
}
