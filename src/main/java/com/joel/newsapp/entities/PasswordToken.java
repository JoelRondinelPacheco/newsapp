package com.joel.newsapp.entities;

import com.joel.newsapp.utils.PasswordTokenType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Entity
@Table(name="password_token")
@Getter
@Setter
@NoArgsConstructor
public class PasswordToken extends Base {
    private String token;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private PasswordTokenType type;
    private boolean valid;

    private Date expireDate;
}
