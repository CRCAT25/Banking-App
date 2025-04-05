package net.thanhdevjava.banking.service.impl;

import net.thanhdevjava.banking.dto.AccountDTO;
import net.thanhdevjava.banking.entity.Account;
import net.thanhdevjava.banking.mapper.AccountMapper;
import net.thanhdevjava.banking.repository.AccountRepository;
import net.thanhdevjava.banking.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDTO createAccount(AccountDTO accountDTO) {
        Account account = AccountMapper.mapToAccount(accountDTO);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDTO(savedAccount);
    }

    @Override
    public AccountDTO getAccountById(Long id) {
        Account foundAccount = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        return AccountMapper.mapToAccountDTO(foundAccount);
    }

    @Override
    public AccountDTO deposit(Long id, Double amount) {
        Account foundAccount = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        double curBalance = foundAccount.getBalance() + amount;
        foundAccount.setBalance(curBalance);
        Account savedAccount = accountRepository.save(foundAccount);

        return AccountMapper.mapToAccountDTO(savedAccount);
    }

    @Override
    public AccountDTO withdraw(Long id, Double amount) {
        Account foundAccount = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if (foundAccount.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        double curBalance = foundAccount.getBalance() - amount;
        foundAccount.setBalance(curBalance);
        Account savedAccount = accountRepository.save(foundAccount);

        return AccountMapper.mapToAccountDTO(savedAccount);
    }

    @Override
    public List<AccountDTO> getAllAccounts() {
        return accountRepository.findAll().stream()
                .map(AccountMapper::mapToAccountDTO)
                .toList();
    }
}
