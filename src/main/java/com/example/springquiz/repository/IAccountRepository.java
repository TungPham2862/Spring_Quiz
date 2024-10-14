package com.example.springquiz.repository;

import com.example.springquiz.model.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IAccountRepository extends JpaRepository<Account, Integer> {
    List<Account> findAllByRole_roleId(Integer roleId);
    Optional<Account> findByUsername(String username);
}
