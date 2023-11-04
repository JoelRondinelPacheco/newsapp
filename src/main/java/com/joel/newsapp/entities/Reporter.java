package com.joel.newsapp.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Table
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
