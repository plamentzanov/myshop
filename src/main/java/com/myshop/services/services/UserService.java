package com.myshop.services.services;

import com.myshop.data.entities.User;
import com.myshop.services.models.UserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    User register(UserServiceModel model);

    User delete(UserServiceModel model);

    User edit(UserServiceModel model);

    List<User> getAllUsers();

    List<User> getAllUsersByRole(String role);

    UserServiceModel getUserByName(String name);

    boolean isUsernameFree(UserServiceModel model);

    boolean isEmailFree(UserServiceModel model);
}
