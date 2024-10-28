package com.example.springquiz.controller;

import com.example.springquiz.model.domain.Account;
import com.example.springquiz.model.dto.AccountDTO;
import com.example.springquiz.model.dto.AuthenRequestDTO;
import com.example.springquiz.model.dto.AuthenResponseDTO;
import com.example.springquiz.model.dto.IntrospectDTO;
import com.example.springquiz.repository.IAccountRepository;
import com.example.springquiz.service.impl.AccountService;
import com.example.springquiz.service.impl.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.text.ParseException;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/accounts")
public class AccountController {

    AccountService accountService;
    IAccountRepository accountRepository;

    @GetMapping
    public ResponseEntity<?> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @GetMapping("/role/{roleId}")
    public ResponseEntity<?> findAccountsByRoleId(@PathVariable int roleId) {
        return ResponseEntity.ok(accountService.getAccountsByRoleId(roleId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findAccountById(@PathVariable int id) {
        return ResponseEntity.ok(accountService.getAccountById(id));
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getCurrentProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<Account> account = accountRepository.findByUsername(username);
        return ResponseEntity.ok(accountService.getAccountById(account.get().getAccountId()));
    }

    @PutMapping("/profile")
    public ResponseEntity<?> updateCurrentProfile(@Valid @RequestBody AccountDTO dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<Account> account = accountRepository.findByUsername(username);
        accountService.updateAccount(account.get().getAccountId(), dto);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/deactivate/{id}")
    public ResponseEntity<?> deactivateAccountById(@PathVariable int id) {
        accountService.deactivateAccountById(id);
        return ResponseEntity.ok().build();
    }
}
