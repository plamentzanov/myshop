package com.myshop.data.entities;

import com.myshop.data.entities.base.BaseEntity;
import org.springframework.security.core.GrantedAuthority;

public class Role extends BaseEntity implements GrantedAuthority {

    private String authority;

    public Role(String authority) {
        this.authority = authority;
    }

    public Role() {
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
