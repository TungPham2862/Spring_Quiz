package com.example.springquiz.service.impl;

import com.example.springquiz.enumeration.ErrorCode;
import com.example.springquiz.exception.CustomizedRuntimeException;
import com.example.springquiz.model.domain.Account;
import com.example.springquiz.model.dto.AuthenRequestDTO;
import com.example.springquiz.model.dto.AuthenResponseDTO;
import com.example.springquiz.model.dto.IntrospectDTO;
import com.example.springquiz.repository.IAccountRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class AuthenticationService {

    private final IAccountRepository accountRepository;
    private final String encryptionKey = "h61e6r+A3LQH4PdQWVnQxwMQ2W8CGIv+kaPl2kyB0dr9XcpMOVYCCBMGS6eD0Oh";

    public AuthenResponseDTO authenticate(AuthenRequestDTO authenRequestDTO) {
        Optional<Account> account = accountRepository.findByUsername(authenRequestDTO.getUsername());
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        boolean result = account.isPresent() && passwordEncoder.matches(authenRequestDTO.getPassword(), account.get().getPassword());
        if (!result) throw new CustomizedRuntimeException(ErrorCode.WRONG_CREDENTIALS);
        String token = generateToken(account.get().getUsername());
        return AuthenResponseDTO.builder()
                .token(token)
                .authenticated(true)
                .build();
    }

    public String generateToken(String username) {
        Account account = accountRepository.findByUsername(username).get();
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(username)
                .issuer("admin1")
                .issueTime(new Date())
                .expirationTime(new Date(System.currentTimeMillis()+ 1000 * 60 * 60))
                .claim("scope",account.getRole().getRoleName())
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header,payload);
        try{
            jwsObject.sign(new MACSigner(encryptionKey.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("cannot create token");
            throw new RuntimeException(e);
        }

    }

    public IntrospectDTO introspect(String token) throws JOSEException, ParseException {
        JWSVerifier verifier = new MACVerifier(encryptionKey.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);
        boolean verified = signedJWT.verify(verifier);
        Date expirationDate = signedJWT.getJWTClaimsSet().getExpirationTime();
        return IntrospectDTO.builder()
                .valid(verified && expirationDate.after(new Date()))
                .build();
    }
}
