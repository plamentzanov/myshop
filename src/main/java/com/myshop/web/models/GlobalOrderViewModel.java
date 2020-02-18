package com.myshop.web.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GlobalOrderViewModel {

    private String id;

    private UserViewModel user;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date orderDate;

    private List<ArchivedOrderViewModel> orders;

    private boolean isCompleted;
}
