package com.example.springquiz.controller;

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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.text.ParseException;
@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

    AccountService accountService;
    IAccountRepository accountRepository;
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
    public ResponseEntity<?> authenticate(@RequestBody AuthenRequestDTO authenRequestDTO) {
        AuthenResponseDTO result = authenticationService.authenticate(authenRequestDTO);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/introspect")
    public ResponseEntity<?> introspect(@RequestParam String token) throws ParseException, JOSEException {
        IntrospectDTO result = authenticationService.introspect(token);
        return ResponseEntity.ok(result);
    }
}
