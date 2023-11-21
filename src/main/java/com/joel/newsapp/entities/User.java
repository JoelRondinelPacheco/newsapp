package com.joel.newsapp.entities;

import com.joel.newsapp.utils.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Entity(name = "users")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User extends Base implements UserDetails {
    private String name;
    private String lastname;
    @Column(unique = true)
    private String email;
    private String password;
    private String displayName;
    @Enumerated(EnumType.STRING)
    private Role role;
    private Boolean enabled;
    private Boolean active;
    @OneToMany(mappedBy = "authorComment", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToOne
    @JoinColumn(name="id_image", referencedColumnName = "id")
    private Image image;


    public User(String name, String email, String password, Image image) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.image = image;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = role.getPermission().stream()
                .map(permissionEnum -> new SimpleGrantedAuthority(permissionEnum.name()))
                .collect(Collectors.toList());

        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.name()));
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.getEnabled();
    }
}
