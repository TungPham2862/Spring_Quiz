package com.example.springquiz.model.domain;

import com.example.springquiz.Enum.RoleName;
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
    
    @Enumerated(EnumType.STRING)
    private RoleName roleName;
    private String status;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private Set<Account> accounts;
}
