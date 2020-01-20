package com.myshop.services.services.implementations;

import com.myshop.data.entities.Role;
import com.myshop.data.repositories.RoleRepository;
import com.myshop.services.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void seedRoles() {
        this.roleRepository.saveAndFlush(new Role("ADMIN"));
        this.roleRepository.saveAndFlush(new Role("MODERATOR"));
        this.roleRepository.saveAndFlush(new Role("USER"));
    }
}
