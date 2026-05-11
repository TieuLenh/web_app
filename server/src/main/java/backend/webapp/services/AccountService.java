package backend.webapp.services;

import backend.webapp.models.Account;
import backend.webapp.repositories.AccountRepo;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class AccountService {
    private AccountRepo accountRepo;
    
    public AccountService(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    private BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
    // Logic Đăng ký
    public Account register(String email, String password) {
        if (accountRepo.existsByEmail(email)) {
            return null;
        }

        Account account = new Account();
        account.setEmail(email);
        account.setUsername(account.getEmail().split("@")[0]); // Đặt username bằng phần trước @ của email
        account.setPassword(pwEncoder.encode(password)); // Mã hóa mật khẩu trước khi lưu
        account.setRole(RoleService.getDefaultRole()); // Gán role mặc định (USER)
        
        accountRepo.save(account);
        return account;
    }

    // Logic Đăng nhập
    public Account login(String email, String password) {
        Account account = accountRepo.findByEmail(email);
        if (account != null && pwEncoder.matches(password, account.getPassword())) {
            return account;
        }
        return null;
    }

    public java.util.List<Account> getAllAccounts() {
        return accountRepo.findAll();
    }

    public Account getProfile(String email) {
        return accountRepo.findByEmail(email);
    }
}