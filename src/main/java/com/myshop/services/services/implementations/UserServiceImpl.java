package com.myshop.services.services.implementations;

import com.myshop.data.entities.Role;
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
import java.util.Set;
import java.util.stream.Collectors;

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
    public void register(UserServiceModel model) {

        User user = this.modelMapper.map(model, User.class);

        if (this.userRepository.count() == 0) {
            this.roleService.seedRoles();
            user.setAuthorities(new HashSet<>(this.roleRepository.findAll()));
        } else {
            user.setAuthorities(new HashSet<>(Collections.singletonList(this.roleRepository.findByAuthority("USER"))));
        }

        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        this.userRepository.saveAndFlush(user);
    }

    @Override
    public List<UserServiceModel> getAllUsers() {
        return this.userRepository.findAll()
                .stream()
                .map(u -> this.modelMapper.map(u, UserServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserServiceModel getUserByName(String name) {
        return this.modelMapper.map(
                this.userRepository.findByUsername(name),
                UserServiceModel.class
        );
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

    @Override
    public UserServiceModel getUserById(String id) {
        return this.modelMapper.map(this.userRepository.findById(id).get(), UserServiceModel.class);
    }

    @Override
    public void makeModerator(UserServiceModel user) {
        Role role = this.roleRepository.findByAuthority("MODERATOR");
        User userUpdate = this.userRepository.findById(user.getId()).get();
        Set<Role> authorities = userUpdate.getAuthorities();
        authorities.add(role);
        userUpdate.setAuthorities(authorities);
        this.userRepository.saveAndFlush(userUpdate);
    }

}
