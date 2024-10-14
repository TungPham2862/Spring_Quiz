package com.example.springquiz.repository;

import com.example.springquiz.model.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Role, Integer> {
    Role findByRoleName(String name);
}
