package ua.com.alevel.Finance.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.com.alevel.Finance.persistence.entity.Account;
import ua.com.alevel.Finance.persistence.entity.Client;
import ua.com.alevel.Finance.persistence.entity.CreditOperation;
import ua.com.alevel.Finance.persistence.entity.DebitOperation;
import ua.com.alevel.Finance.persistence.repository.AccountRepository;
import ua.com.alevel.Finance.persistence.repository.ClientRepository;

import java.io.File;
import java.math.BigDecimal;
import java.util.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AccountServiceTest {

    private static final Long ID1 = 1L;
    private static final String FULL_NAME = "FULL NAME FOR TEST";
    private static final String EMAIL = "test@email.com";
    private static final String ACCOUNT_NAME = "First account name";
    private static final int ACCOUNT_NUMBER = 123456;
    private static final BigDecimal ACCOUNT_BALANCE = new BigDecimal(1000);

    private static final List<CreditOperation> CREDIT_OPERATION_LIST = new ArrayList<>();
    private static final List<DebitOperation> DEBIT_OPERATION_LIST = new ArrayList<>();
    private static Account account1;
    private static Client client1;
    private static CreditOperation creditOperation;
    private static DebitOperation debitOperation;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AccountService accountService;

    @BeforeAll
    public static void setUp() {
        client1 = new Client();
        client1.setId(ID1);
        client1.setFullName(FULL_NAME);
        client1.setEmail(EMAIL);
        account1 = new Account();
        account1.setId(ID1);
        account1.setAccountName(ACCOUNT_NAME);
        account1.setAccountNumber(ACCOUNT_NUMBER);
        account1.setBalance(ACCOUNT_BALANCE);
        account1.setClient(client1);
        account1.setCreditOperations(CREDIT_OPERATION_LIST);
        account1.setDebitOperations(DEBIT_OPERATION_LIST);
        client1.setAccounts(Collections.singletonList(account1));
        creditOperation = new CreditOperation();
        debitOperation = new DebitOperation();
        creditOperation.setOperationDate(new Date());
        debitOperation.setOperationDate(new Date());
        CREDIT_OPERATION_LIST.add(creditOperation);
        DEBIT_OPERATION_LIST.add(debitOperation);
    }

    @Test
    @Order(1)
    void shouldBeCreateAccount() {
        clientRepository.save(client1);
        accountRepository.save(account1);
        Assertions.assertNotNull(account1.getId());
    }

    @Test
    @Order(2)
    void shouldBeUpdateAccount() {
        clientRepository.save(client1);
        accountRepository.save(account1);
        Assertions.assertEquals(account1.getId(), accountRepository.findById(ID1).get().getId());
    }

    @Test
    @Order(3)
    void shouldFindAccountById() {
        clientRepository.save(client1);
        accountRepository.save(account1);
        Optional<Account> foundAcc = accountRepository.findById(ID1);
        Account expectedAcc = foundAcc.orElse(null);
        Assertions.assertNotNull(expectedAcc);
    }

    @Test
    @Order(4)
    void shouldFindAllAccounts() {
        clientRepository.save(client1);
        accountRepository.save(account1);
        Collection<Account> foundAccounts = accountRepository.findAll();
        Assertions.assertNotNull(foundAccounts);
    }

    @Test
    @Order(5)
    void shouldBeDeleteAccount() {
        clientRepository.save(client1);
        accountRepository.save(account1);
        accountRepository.deleteById(ID1);
        Assertions.assertTrue(accountRepository.findById(ID1).isPresent());
    }

    @Test
    @Order(6)
    void shouldFindAccountsByClientId() {
        clientRepository.save(client1);
        accountRepository.save(account1);
        Collection<Account> foundAccounts = accountRepository.findAccountsByClientId(ID1);
        Assertions.assertNotNull(foundAccounts);
    }

    @Test
    @Order(7)
    void shouldFindAccountByAccountNumber() {
        clientRepository.save(client1);
        accountRepository.save(account1);
        Optional<Account> foundAcc = accountRepository.findAccountByAccountNumber(ACCOUNT_NUMBER);
        Account expectedAcc = foundAcc.orElse(null);
        Assertions.assertNotNull(expectedAcc);
    }

    @Test
    @Order(8)
    void shouldExportToCsv() {
        clientRepository.save(client1);
        accountRepository.save(account1);
        Date fromDate = new Date();
        Date toDate = new Date();
        accountService.exportToCsv(account1, fromDate, toDate);
        File file = new File("export.csv");
        Assertions.assertTrue(file.exists());
    }
}
