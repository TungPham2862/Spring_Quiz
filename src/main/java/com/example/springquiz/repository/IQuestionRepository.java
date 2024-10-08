package com.example.springquiz.repository;

import com.example.springquiz.model.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IQuestionRepository extends JpaRepository<Question, Long> {
}
