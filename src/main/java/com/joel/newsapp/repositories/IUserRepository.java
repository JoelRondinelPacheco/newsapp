package com.joel.newsapp.repositories;

import com.joel.newsapp.dtos.users.UserInfoDTO;
import com.joel.newsapp.dtos.users.UserLoginDTO;
import com.joel.newsapp.entities.Image;
import com.joel.newsapp.entities.User;
import com.joel.newsapp.exceptions.NotFoundException;
import com.joel.newsapp.utils.Role;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, String> {
    Page<User> findByRoleAndEnabledAndVerified(Role role, Boolean enabled, Boolean verified, Pageable pageable);
    Page<User> findByRoleAndEnabled(Role role, Boolean active, Pageable page);
    Optional<User> findByEmail(String email);
}
