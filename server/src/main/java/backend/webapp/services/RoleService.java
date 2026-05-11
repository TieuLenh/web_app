package backend.webapp.services;

import org.springframework.stereotype.Service;

import backend.webapp.repositories.RoleRepo;
import backend.webapp.models.Role;

@Service
public class RoleService {
    private static RoleRepo roleRepo;
    public RoleService(RoleRepo roleRepo) {
        RoleService.roleRepo = roleRepo;
    }

    // Lấy role mặc định (USER) để gán cho tài khoản mới
    public static Role getDefaultRole() {
        return roleRepo.findByName("USER");
    }
}
