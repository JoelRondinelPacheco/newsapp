package com.joel.newsapp.dtos.users;

import com.joel.newsapp.entities.User;

public interface Employee {
    User getUser();
    Double getMonthlySalary();
    Boolean getEnabled();
    String getId();
}
