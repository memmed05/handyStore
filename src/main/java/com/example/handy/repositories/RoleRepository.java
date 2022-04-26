package com.example.handy.repositories;

import com.example.handy.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role getByName(String name);

    @Query("select r from Role r where r.name = ?1")
    List<Role> findRolesByName(String role);
}
