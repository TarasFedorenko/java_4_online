package ua.com.alevel.Finance.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.Finance.persistence.entity.Client;
import ua.com.alevel.Finance.service.ClientService;

@Controller
@RequestMapping("/clients")
@AllArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public String showAll(Model model) {
        model.addAttribute("clients", clientService.findAll());
        return "client/show_all";
    }

    @GetMapping("/{id}")
    public String showById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("client", clientService.findById(id));
        return "client/show_by_id";
    }

    @GetMapping("/new")
    public String createClientForm(Model model) {
        model.addAttribute("client", new Client());
        return "client/new";
    }

    @PostMapping
    public String createClient(@ModelAttribute("client") @Valid Client client,
                               BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "client/new";

        clientService.create(client);
        return "redirect:/clients";
    }

    @GetMapping("/{id}/edit")
    public String editClientForm(Model model, @PathVariable("id") Long id) {
        model.addAttribute("client", clientService.findById(id));
        return "client/edit";
    }

    @PatchMapping("/{id}")
    public String editClient(@ModelAttribute("client") @Valid Client client, BindingResult bindingResult, @PathVariable("id") Long id) {

        if (bindingResult.hasErrors())
            return "client/edit";

        clientService.update(id, client);
        return "redirect:/clients";
    }

    @GetMapping("/delete/{id}")
    public String deleteClient(@PathVariable("id") Long id) {
        clientService.delete(id);
        return "redirect:/clients";
    }
}
