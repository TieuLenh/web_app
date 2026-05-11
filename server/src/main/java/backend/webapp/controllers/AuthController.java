package backend.webapp.controllers;


import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import java.util.Map;
import backend.webapp.DTOs.AuthRequest;
import backend.webapp.models.Account;
import backend.webapp.services.AccountService;
import backend.webapp.services.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import backend.webapp.DTOs.Response;

@RestController
@RequestMapping("/api")
public class AuthController {
    
    private AccountService accountService;
    public AuthController(AccountService accountService) {
        this.accountService = accountService;
    }

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest loginRequest) {
        // In ra để kiểm tra xem dữ liệu đã sang chưa
        System.out.println("Email: " + loginRequest.getEmail());
        System.out.println("Password: " + loginRequest.getPassword());
        Account account = accountService.login(loginRequest.getEmail(), loginRequest.getPassword());
        if (account != null) {
            if("ACTIVATED".equals(account.getStatus())){
                String token = jwtService.generateToken(account.getId(), account.getRole().getName());
                return ResponseEntity.ok(
                    new Response(
                        true, 
                        "Đăng nhập thành công!", 
                        Map.of(
                            "token", token,
                            "username", account.getUsername(),
                            "role", account.getRole().getName() 
                        )
                    )
                );
            }
            else {
                return ResponseEntity.status(401).body(
                    new Response(
                        false, 
                        "Your account is " + account.getStatus().toLowerCase(), 
                        Map.of(
                            
                        )
                    )
                );
            }
            
        } else {
            return ResponseEntity.status(401).body(new Response(false, "Sai tài khoản hoặc mật khẩu", null));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest registerRequest) {
        Account account = accountService.register(registerRequest.getEmail(), registerRequest.getPassword());
        if (account != null) {
            String token = jwtService.generateToken(account.getId(), account.getRole().getName());
            return ResponseEntity.ok(
                new Response(
                    true, 
                    "Đăng ký thành công!", 
                    Map.of(
                        "token", token, 
                        "username", account.getUsername(),
                        "role", account.getRole().getName()
                    )
                )
            );
        }
        return ResponseEntity.badRequest()
            .body(new Response(false, "Email already in use!", null));
    }

    @GetMapping("/me")
    public ResponseEntity<?> getProfile(
            HttpServletRequest request
    ) {
        return null;
    }
}
