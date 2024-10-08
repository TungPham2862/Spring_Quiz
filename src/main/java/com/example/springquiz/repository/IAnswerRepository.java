package com.example.springquiz.repository;

import com.example.springquiz.model.domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAnswerRepository extends JpaRepository<Answer, Long> {
}
