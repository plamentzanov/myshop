package com.myshop.web.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserProfileModel {

    private String username;
    private String address;
    private String email;
    private List<GlobalOrderProfileViewModel> orders;
}
