package com.joel.newsapp.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@DiscriminatorValue("reporter")
@Getter
@Setter
public class Reporter extends User {
    private Double monthlySalary;

    //@OneToMany(fetch=FetchType.EAGER)
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<News> myNews;
}
