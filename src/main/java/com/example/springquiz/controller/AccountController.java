package com.example.springquiz.controller;

import com.example.springquiz.model.dto.AccountDTO;
import com.example.springquiz.model.dto.AuthenDTO;
import com.example.springquiz.model.dto.IntrospectDTO;
import com.example.springquiz.service.impl.AccountService;
import com.example.springquiz.service.impl.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.text.ParseException;

@RestController
@AllArgsConstructor
public class AccountController {

    AccountService accountService;
    AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<?> createNewAccount(@Valid @RequestBody AccountDTO dto, UriComponentsBuilder uriComponentsBuilder) {
        int accountId = accountService.createNewAccount(dto);
        UriComponents uriComponents = uriComponentsBuilder
                .path("/accounts/{id}")
                .buildAndExpand(accountId);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestParam String username, @RequestParam String password, UriComponentsBuilder uriComponentsBuilder) {
        AuthenDTO result = authenticationService.authenticate(username,password);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/introspect")
    public ResponseEntity<?> introspect(@RequestParam String token) throws ParseException, JOSEException {
        IntrospectDTO result = authenticationService.introspect(token);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/accounts")
    public ResponseEntity<?> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @GetMapping("/accounts/role/{roleId}")
    public ResponseEntity<?> findAccountsByRoleId(@PathVariable int roleId) {
        return ResponseEntity.ok(accountService.getAccountsByRoleId(roleId));
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<?> findAccountById(@PathVariable int id) {
        return ResponseEntity.ok(accountService.getAccountById(id));
    }

    @PutMapping("/account/{id}")
    public ResponseEntity<?> updateAccountById(@PathVariable int id, @Valid @RequestBody AccountDTO dto) {
        accountService.updateAccount(id, dto);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/deactivate/{id}")
    public ResponseEntity<?> deactivateAccountById(@PathVariable int id) {
        accountService.deactivateAccountById(id);
        return ResponseEntity.ok().build();
    }
}
