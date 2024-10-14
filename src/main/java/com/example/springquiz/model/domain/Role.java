package com.example.springquiz.model.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleId;
    private String roleName;
    private String status;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private Set<Account> accounts;
}
