package com.java.security.service;

import com.java.security.model.Role;
import com.java.security.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role addRole(Role role) {
        return this.roleRepository.save(role);

    }
}
