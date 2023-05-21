package ua.com.alevel.Finance.service;

import java.math.BigDecimal;


public interface OperationService {
    void transferMoney(Long fromAccountId, Long toAccountId, BigDecimal amount);
}
