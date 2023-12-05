package com.joel.newsapp.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity(name="reporters")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
public class Reporter extends Base {
    @OneToOne
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;
    private Double monthlySalary;
    private Boolean enabled;

    //@OneToMany(fetch=FetchType.EAGER)
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<News> myNews;

}
