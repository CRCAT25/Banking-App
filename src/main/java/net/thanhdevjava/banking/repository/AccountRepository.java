package net.thanhdevjava.banking.repository;

import net.thanhdevjava.banking.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
