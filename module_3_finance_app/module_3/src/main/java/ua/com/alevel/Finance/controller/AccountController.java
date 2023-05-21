package ua.com.alevel.Finance.controller;

import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.Finance.persistence.entity.Account;
import ua.com.alevel.Finance.service.AccountService;
import ua.com.alevel.Finance.service.ClientService;

import java.util.Date;

@Controller
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;
    private final ClientService clientService;


    private Long clientId;

    public AccountController(AccountService accountService, ClientService clientService) {
        this.accountService = accountService;
        this.clientService = clientService;
    }

    @GetMapping("/{id}/new")
    public String createAccountForm(@PathVariable("id") Long id, Model model) {
        clientId = id;
        model.addAttribute("account", new Account());

        return "account/new";
    }

    @PostMapping
    public String createAccount(@ModelAttribute("account") @Valid Account account,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "account/new";

        account.setClient(clientService.findById(clientId));
        accountService.create(account);
        return "redirect:/clients/" + clientId;
    }

    @GetMapping("/{id}")
    public String showById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("account", accountService.findById(id));
        return "account/show_by_id";
    }

    @GetMapping("/delete/{id}")
    public String deleteAccount(@PathVariable("id") Long id) {
        accountService.delete(id);
        return "redirect:/clients/" + clientId;
    }

    @GetMapping("/{id}/export")
    public String exportCSVForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("account", accountService.findById(id));
        return "account/export";
    }

    @PostMapping("/download")
    public String exportCSV(@RequestParam("fromDate") @DateTimeFormat(pattern = "dd/MM/yyyy") Date dateFrom,
                            @RequestParam("toDate") @DateTimeFormat(pattern = "dd/MM/yyyy") Date dateTo,
                            @RequestParam("accountId") String accountId) {
        accountService.exportToCsv(accountService.findById(Long.valueOf(accountId)), dateFrom, dateTo);
        return "redirect:/clients";
    }
}
