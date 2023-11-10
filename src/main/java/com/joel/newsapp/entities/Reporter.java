package com.joel.newsapp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@DiscriminatorValue("reporter")
public class Reporter extends User {
    private Integer monthlySalary;

    //@OneToMany(fetch=FetchType.EAGER)
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<News> myNews;
}
