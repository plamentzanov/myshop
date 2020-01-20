package com.myshop.services.services;

import com.myshop.data.entities.User;
import com.myshop.services.models.UserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User register(UserServiceModel model);
}
