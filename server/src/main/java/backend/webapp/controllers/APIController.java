package backend.webapp.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.webapp.models.Account;

import backend.webapp.services.AccountService;


@RestController
@RequestMapping("/api")
public class APIController {
    private AccountService accountService;
    

    public APIController( AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/users")
    public List<Account> getUsers() {
        return accountService.getAllAccounts();
    }
    
    @GetMapping("/profile")
    public Account getProfile(String email) {
        return accountService.getProfile(email);
    }
}
