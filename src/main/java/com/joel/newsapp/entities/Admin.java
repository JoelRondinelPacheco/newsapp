package com.joel.newsapp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity(name = "admins")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
public class Admin extends Base {
    private Double monthlySalary;
    private Boolean enabled;
    @OneToOne
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;
}
