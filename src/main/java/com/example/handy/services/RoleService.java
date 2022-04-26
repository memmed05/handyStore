package com.example.handy.services;

import com.example.handy.model.Role;
import com.example.handy.model.User;

public interface RoleService {
    Role findByRole(String role);

    void addRoleToUser(User user, String roleName);
}
