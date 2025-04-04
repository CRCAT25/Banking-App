package net.thanhdevjava.banking.mapper;

import net.thanhdevjava.banking.dto.AccountDTO;
import net.thanhdevjava.banking.entity.Account;


public class AccountMapper {

    public static AccountDTO mapToAccountDTO(Account account) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(account.getId());
        accountDTO.setAccountHolderName(account.getAccountHolderName());
        accountDTO.setBalance(account.getBalance());
        return accountDTO;
    }

    public static Account mapToAccount(AccountDTO accountDTO) {
        Account account = new Account();
        account.setId(accountDTO.getId());
        account.setAccountHolderName(accountDTO.getAccountHolderName());
        account.setBalance(accountDTO.getBalance());
        return account;
    }
}
