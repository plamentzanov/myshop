package com.myshop.services.services.implementations;

import com.myshop.data.entities.User;
import com.myshop.data.repositories.RoleRepository;
import com.myshop.data.repositories.UserRepository;
import com.myshop.services.models.UserServiceModel;
import com.myshop.services.services.RoleService;
import com.myshop.services.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService, RoleRepository roleRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public User register(UserServiceModel model) {

        User user = this.modelMapper.map(model, User.class);

        if (this.userRepository.count() == 0) {
            roleService.seedRoles();
            user.setAuthorities(new HashSet<>(this.roleRepository.findAll()));
        } else {
            user.setAuthorities(new HashSet<>(Collections.singletonList(this.roleRepository.findByAuthority("USER"))));
        }
        return this.userRepository.saveAndFlush(user);
    }
}
