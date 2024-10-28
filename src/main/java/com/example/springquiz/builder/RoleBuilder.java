package com.example.springquiz.builder;

import com.example.springquiz.model.domain.Role;
import com.example.springquiz.model.dto.AccountDTO;
import com.example.springquiz.model.dto.RoleDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class RoleBuilder {

    private final ModelMapper modelMapper;

    public Role build(RoleDTO dto) {
        Role model = modelMapper.map(dto, Role.class);
        return model;
    }

    public Optional<RoleDTO> build(Role domain) {
        RoleDTO dto = modelMapper.map(domain, RoleDTO.class);
        return Optional.of(dto);
    }

    public Role build(AccountDTO dto, Role domain) {
        modelMapper.map(dto, domain);
        return domain;
    }
}
