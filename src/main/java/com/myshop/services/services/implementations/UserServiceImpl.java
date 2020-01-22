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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService, RoleRepository roleRepository, ModelMapper modelMapper, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = this.userRepository.findByUsername(username);

        if (userDetails == null) {
            throw  new UsernameNotFoundException("User not found!");
        }

        return userDetails;
    }

    @Override
    public User register(UserServiceModel model) {

        User user = this.modelMapper.map(model, User.class);

        if (this.userRepository.count() == 0) {
            this.roleService.seedRoles();
            user.setAuthorities(new HashSet<>(this.roleRepository.findAll()));
        } else {
            user.setAuthorities(new HashSet<>(Collections.singletonList(this.roleRepository.findByAuthority("USER"))));
        }

        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        return this.userRepository.saveAndFlush(user);
    }

    @Override
    public User delete(UserServiceModel model) {
        return null;
    }

    @Override
    public User edit(UserServiceModel model) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public List<User> getAllUsersByRole(String role) {
        return null;
    }

    @Override
    public boolean isUsernameFree(UserServiceModel model) {
        User user = this.userRepository.findByUsername(model.getUsername());

        return user == null;
    }

    @Override
    public boolean isEmailFree(UserServiceModel model) {
        User user = this.userRepository.findByEmail(model.getEmail());

        return user == null;
    }

}
