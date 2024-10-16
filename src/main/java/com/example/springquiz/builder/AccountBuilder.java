package com.example.springquiz.builder;

import com.example.springquiz.model.domain.Account;
import com.example.springquiz.model.domain.Answer;
import com.example.springquiz.model.dto.AccountDTO;
import com.example.springquiz.model.dto.AnswerDTO;
import com.example.springquiz.repository.IQuestionRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class AccountBuilder {

    private final ModelMapper modelMapper;
    public Account build(AccountDTO dto) {
        Account model = modelMapper.map(dto, Account.class);
        return model;
    }

    public Optional<AccountDTO> build(Account domain) {
        AccountDTO dto = modelMapper.map(domain, AccountDTO.class);
        dto.setRoleName(domain.getRole().getRoleName());
        return Optional.of(dto);
    }

    public Account build(AccountDTO dto, Account domain) {
        modelMapper.map(dto, domain);
        return domain;
    }
}
