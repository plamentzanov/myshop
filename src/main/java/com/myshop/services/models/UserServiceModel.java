package com.myshop.services.models;

import com.myshop.services.services.GlobalOrderService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserServiceModel {

    private String id;
    private String username;
    private String password;
    private String email;
    private String address;
    private List<OrderServiceModel> cart;
    private Set<RoleServiceModel> authorities;
    private List<GlobalOrderServiceModel> orders;
}
