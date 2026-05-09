package backend.webapp.controllers;


import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import java.util.Map;
import backend.webapp.DTOs.authRequest;
import backend.webapp.models.Account;
import backend.webapp.services.AccountService;


@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody authRequest loginRequest) {
        // In ra để kiểm tra xem dữ liệu đã sang chưa
        System.out.println("Email: " + loginRequest.getEmail());
        System.out.println("Password: " + loginRequest.getPassword());
        Account account = new Account();

        account.setEmail(loginRequest.getEmail());
        account.setPassword(loginRequest.getPassword());

        boolean isAuthenticated = accountService.login(loginRequest.getEmail(), loginRequest.getPassword());
        if (isAuthenticated) {
            return ResponseEntity.ok(Map.of("message", "Đăng nhập thành công!", "user", loginRequest.getEmail()));
        } else {
            return ResponseEntity.status(401).body(Map.of("error", "Sai tài khoản hoặc mật khẩu"));
        }
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody authRequest registerRequest) {

        Account account = new Account();

        account.setEmail(registerRequest.getEmail());
        account.setPassword(registerRequest.getPassword());

        String result = accountService.register(account);

        if ("Register success!".equals(result)) {
            return ResponseEntity.ok(
                Map.of("message", result)
            );
        }

        return ResponseEntity.badRequest()
                .body(Map.of("message", result));
    }

}
