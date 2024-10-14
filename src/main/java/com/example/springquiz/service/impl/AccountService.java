package com.example.springquiz.service.impl;

import com.example.springquiz.builder.AccountBuilder;
import com.example.springquiz.exception.CustomizedNotFoundException;
import com.example.springquiz.model.domain.Account;
import com.example.springquiz.model.domain.Role;
import com.example.springquiz.model.dto.AccountDTO;
import com.example.springquiz.model.dto.AuthenDTO;
import com.example.springquiz.repository.IAccountRepository;
import com.example.springquiz.repository.IRoleRepository;
import com.example.springquiz.service.IAccountService;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class AccountService implements IAccountService {

    private final IAccountRepository accountRepository;
    private final AccountBuilder accountBuilder;

    private final IRoleRepository roleRepository;
    @Override
    public int createNewAccount(AccountDTO dto) {
        Role role = roleRepository.findByRoleName(dto.getRoleName());
        Account account = accountBuilder.build(dto);
        account.setRole(role);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        account.setPassword(passwordEncoder.encode(dto.getPassword()));
        accountRepository.save(account);
        return account.getAccountId();
    }

    @Override
    public List<Optional<AccountDTO>> getAllAccounts() {
        return accountRepository.findAll()
                .stream()
                .map(accountBuilder::build)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AccountDTO> getAccountById(int id) {
        return accountRepository.findById(id)
                .map(accountBuilder::build)
                .orElseThrow(() -> new CustomizedNotFoundException(String.format("Account with id %s not found", id)));
    }

    @Override
    public List<Optional<AccountDTO>> getAccountsByRoleId(int roleId) {
        return accountRepository.findAllByRole_roleId(roleId)
                .stream()
                .map(accountBuilder::build)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AccountDTO> updateAccount(int id, AccountDTO dto) {
        return accountRepository.findById(id)
                .map(model -> accountBuilder.build(dto,model))
                .map(accountRepository::save)
                .map(accountBuilder::build)
                .orElseThrow(() -> new CustomizedNotFoundException(String.format("Account with id %s not found", id)));
    }

    @Override
    public void deactivateAccountById(int id) {
        AccountDTO dto = getAccountById(id).get();
        String status = dto.getStatus().equals("Active") ? "Inactive" : "Active";
        dto.setStatus(status);
        accountRepository.findById(id)
                .map(model -> accountBuilder.build(dto,model))
                .map(accountRepository::save);
    }


}
