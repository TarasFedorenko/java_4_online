package ua.com.alevel.Finance.service;

import ua.com.alevel.Finance.persistence.entity.Account;

import java.util.Collection;
import java.util.Date;

public interface AccountService {

    void exportToCsv(Account account, Date fromDate, Date toDate);

    Collection<Account> findAccountsByClientId(Long id);

    Account findAccountByAccountNumber(int accountNumber);

    void create(Account account);

    void update(Long id, Account account);

    Account findById(Long id);

    Collection<Account> findAll();

    void delete(Long id);

}
