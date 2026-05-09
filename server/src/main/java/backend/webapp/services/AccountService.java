package backend.webapp.services;

import backend.webapp.models.Account;
import backend.webapp.repositories.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class AccountService {

    @Autowired
    private AccountRepo accountRepo;
    // private BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();

    // Logic Đăng ký
    public String register(Account account) {
        if (accountRepo.existsByEmail(account.getEmail())) {
            return "Email already in use!";
        }
        account.setUsername(account.getEmail().split("@")[0]); // Đặt username bằng phần trước @ của email
        // account.setPassword(pwEncoder.encode(account.getPassword())); // Mã hóa mật khẩu trước khi lưu
        accountRepo.save(account);
        return "Register success!";
    }

    // Logic Đăng nhập
    public boolean login(String email, String password) {
        Account account = accountRepo.findByEmail(email);
        if (account != null) {
            // return pwEncoder.matches(password, account.getPassword());
            return password.equals(account.getPassword());
        }
        return false;
    }
}