package com.joel.newsapp.repositories;

import com.joel.newsapp.dtos.users.UserLoginDTO;
import com.joel.newsapp.entities.Image;
import com.joel.newsapp.entities.User;
import com.joel.newsapp.exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    @Query("SELECT user FROM User user WHERE user.email = :username")
    Optional<User> findUser(@Param("username") String email);
    @Query("SELECT NEW com.joel.eggnews.dtos.users.UserLoginDTO(u.email, u.password, u.role) FROM User u WHERE u.email = :username")
    Optional<UserLoginDTO> findByUsername(@Param("username") String username);
    @Query("SELECT NEW com.joel.eggnews.dtos.users.UserLoginDTO(u.email, u.password, u.role) FROM User u WHERE u.id = :userId")
    Optional<UserLoginDTO> infoDTOById(@Param("userId") Long id);
    @Query("SELECT Image i from User u WHERE u.id = :userId")
    Image getImageById(@Param("userId") Long userId) throws NotFoundException;
    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.monthlysalary = :salary, u.enabled = :active WHERE u.id = :idU")
    int updateSalaryAndEnabled(@Param("salary") Integer salary, @Param("active") boolean active, @Param("idU") Long id);
    @Query("SELECT reporter FROM User reporter WHERE reporter.role = REPORTER")
    List<User> finAllReporters();
    @Query("SELECT reporter FROM User reporter WHERE reporter.role = ADMIN")
    List<User> finAllAdmins();
    @Query("SELECT reporter FROM User reporter WHERE reporter.role = USER")
    List<User> finAllUsers();
}
