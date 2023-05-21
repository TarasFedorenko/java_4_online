package ua.com.alevel.Finance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.Finance.persistence.entity.Account;
import ua.com.alevel.Finance.service.AccountService;
import ua.com.alevel.Finance.service.ClientService;
import ua.com.alevel.Finance.service.OperationService;

import java.math.BigDecimal;


@Controller
@RequestMapping("/operations")
public class OperationController {

    private final OperationService operationService;
    private final ClientService clientService;
    private final AccountService accountService;

    private Long clientId;

    public OperationController(OperationService operationService, ClientService clientService, AccountService accountService) {
        this.operationService = operationService;
        this.clientService = clientService;
        this.accountService = accountService;
    }


    @GetMapping("/{client_id}/{account_id}/new")
    public String createTransferForm(@PathVariable("client_id") Long clientId,
                                     @PathVariable("account_id") Long accountId,
                                     Model model) {
        this.clientId = clientId;

        model.addAttribute("sender", clientService.findById(clientId));
        model.addAttribute("sender_account", accountService.findById(accountId));
        return "operation/new";
    }

    @PostMapping
    public String createTransfer(@RequestParam("fromAccNumber") String fromAccNumber,
                                 @RequestParam("toAccNumber") String toAccNumber,
                                 @RequestParam("amount") BigDecimal amount) {

        Account fromAccount = accountService.findAccountByAccountNumber(Integer.parseInt(fromAccNumber));
        Account toAccount = accountService.findAccountByAccountNumber(Integer.parseInt(toAccNumber));
        operationService.transferMoney(fromAccount.getId(),
                toAccount.getId(),
                amount);
        return "redirect:/clients/" + clientId;
    }
}
