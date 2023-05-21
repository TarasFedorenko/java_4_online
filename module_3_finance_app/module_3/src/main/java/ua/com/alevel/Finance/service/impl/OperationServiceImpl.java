package ua.com.alevel.Finance.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.Finance.persistence.entity.Account;
import ua.com.alevel.Finance.persistence.entity.Category;
import ua.com.alevel.Finance.persistence.entity.CreditOperation;
import ua.com.alevel.Finance.persistence.entity.DebitOperation;
import ua.com.alevel.Finance.service.AccountService;
import ua.com.alevel.Finance.service.OperationService;

import java.math.BigDecimal;
import java.util.Date;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class OperationServiceImpl implements OperationService {

    private AccountService accountService;

    @Transactional
    public void transferMoney(Long fromAccountId, Long toAccountId, BigDecimal amount) {

        Account fromAccount = accountService.findById(fromAccountId);
        Account toAccount = accountService.findById(toAccountId);

        if (fromAccount.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient balance");
        }

        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        toAccount.setBalance(toAccount.getBalance().add(amount));

        accountService.update(fromAccountId, fromAccount);
        accountService.update(toAccountId, toAccount);

        DebitOperation debitOperation = new DebitOperation();
        debitOperation.setOperationDate(new Date());
        debitOperation.setAmount(amount);
        debitOperation.setSenderAccount(fromAccount);
        debitOperation.setRecipientAccount(toAccount);
        debitOperation.setCategory(Category.DEBIT);

        CreditOperation creditOperation = new CreditOperation();
        creditOperation.setOperationDate(new Date());
        creditOperation.setAmount(amount);
        creditOperation.setSenderAccount(fromAccount);
        creditOperation.setRecipientAccount(toAccount);
        creditOperation.setCategory(Category.CREDIT);

        fromAccount.getCreditOperations().add(creditOperation);
        toAccount.getDebitOperations().add(debitOperation);

        accountService.update(fromAccountId, fromAccount);
        accountService.update(toAccountId, toAccount);
    }
}
