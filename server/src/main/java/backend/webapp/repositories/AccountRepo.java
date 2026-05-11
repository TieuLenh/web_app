package backend.webapp.repositories;

import backend.webapp.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {
    // Tìm kiếm account bằng email để phục vụ đăng nhập
    Account findByEmail(String email);

    // Kiểm tra xem email đã tồn tại chưa để phục vụ đăng ký
    boolean existsByEmail(String email);

    // Kiểm tra xem username đã tồn tại chưa
    boolean existsByUsername(String username);

    void deleteByEmail(String email);
}
