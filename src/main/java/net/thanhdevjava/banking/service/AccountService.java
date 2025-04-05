package net.thanhdevjava.banking.service;

import net.thanhdevjava.banking.dto.AccountDTO;
import net.thanhdevjava.banking.entity.Account;

public interface AccountService {
    AccountDTO createAccount(AccountDTO accountDTO);

    AccountDTO getAccountById(Long id);
}
