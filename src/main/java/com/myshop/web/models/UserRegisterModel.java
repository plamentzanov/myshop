package com.myshop.web.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class UserRegisterModel {

    @NotNull
    @Size(min = 3, max = 15)
    private String username;

    @NotNull
    @Size(min = 6, max = 15)
    private String password;

    @NotNull
    @Size(min = 6, max = 15)
    private String confirmPassword;

    @NotNull
    @Size(min = 7, max = 30)
    private String email;

    @NotNull
    @Size(min = 5, max = 30)
    private String address;
}
