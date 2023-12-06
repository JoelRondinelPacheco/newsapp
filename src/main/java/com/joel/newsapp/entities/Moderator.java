package com.joel.newsapp.entities;

import com.joel.newsapp.dtos.users.Employee;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity(name = "moderators")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
public class Moderator extends Base implements Employee {
    @OneToOne
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;
    private Boolean enabled;
    private Double monthlySalary;
}
