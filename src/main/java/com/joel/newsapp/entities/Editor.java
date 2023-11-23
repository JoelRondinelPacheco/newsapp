package com.joel.newsapp.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity(name = "editores_table")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
public class Editor extends Base {
    private Double monthlySalary;
    private Boolean enabled;
    @OneToOne
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;
}
