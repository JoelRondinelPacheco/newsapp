package com.joel.newsapp.entities;

import jakarta.persistence.Entity;
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
public class Moderator extends Base {
    private Boolean enabled;
    @PrePersist
    private void prePersist() {
        this.enabled = true;
    }
}
