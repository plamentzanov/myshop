package com.myshop.services.services;

import com.myshop.data.entities.User;
import com.myshop.services.models.UserServiceModel;
import com.myshop.web.models.UserViewModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    void register(UserServiceModel model);

    List<UserServiceModel> getAllUsers();

    UserServiceModel getUserByName(String name);

    boolean isUsernameFree(UserServiceModel model);

    boolean isEmailFree(UserServiceModel model);

    UserServiceModel getUserById(String id);

    void makeModerator(UserServiceModel user);
}
