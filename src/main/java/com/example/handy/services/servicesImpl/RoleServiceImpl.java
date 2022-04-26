package com.example.handy.services.servicesImpl;

import com.example.handy.model.Role;
import com.example.handy.model.User;
import com.example.handy.repositories.RoleRepository;
import com.example.handy.services.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepo;

    @Override
    public Role findByRole(String role) {
        return roleRepo.getByName(role);
    }

    @Override
    public void addRoleToUser(User user, String roleName) {
        Role role = new Role();
        role.setName(roleName);
        role.setUser(user);
        roleRepo.save(role);
    }

}
