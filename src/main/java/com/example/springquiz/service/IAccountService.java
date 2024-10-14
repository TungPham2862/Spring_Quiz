package com.example.springquiz.service;

import com.example.springquiz.model.dto.AccountDTO;
import com.example.springquiz.model.dto.AuthenDTO;

import java.util.List;
import java.util.Optional;

public interface IAccountService {
    int createNewAccount(AccountDTO dto);

    List<Optional<AccountDTO>> getAllAccounts();

    Optional<AccountDTO> getAccountById(int id);

    List<Optional<AccountDTO>> getAccountsByRoleId(int questionId);

    Optional<AccountDTO> updateAccount(int id, AccountDTO dto);

    void deactivateAccountById(int id);

}
