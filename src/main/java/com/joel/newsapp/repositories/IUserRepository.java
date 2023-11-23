package com.joel.newsapp.repositories;

import com.joel.newsapp.dtos.users.UserInfoDTO;
import com.joel.newsapp.dtos.users.UserLoginDTO;
import com.joel.newsapp.entities.Image;
import com.joel.newsapp.entities.User;
import com.joel.newsapp.exceptions.NotFoundException;
import com.joel.newsapp.utils.Role;
import jakarta.transaction.Transactional;
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
    List<User> findByRoleAndEnabledAndActive(Role role, Boolean enabled, Boolean active);
    List<User> findByRoleAndEnabled(Role role, Boolean active);
    Optional<User> findByEmail(String email);
}
