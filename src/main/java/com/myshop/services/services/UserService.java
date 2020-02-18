package com.myshop.services.services;

import com.myshop.data.entities.User;
import com.myshop.services.models.UserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    void register(UserServiceModel model);

    String getUsersRole(String id);

    List<UserServiceModel> getAllUsers();

    UserServiceModel getUserByName(String name);

    boolean isUsernameFree(UserServiceModel model);

    boolean isEmailFree(UserServiceModel model);
}
