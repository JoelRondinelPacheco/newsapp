package com.joel.newsapp.repositories;

import com.joel.newsapp.dtos.users.UserInfoDTO;
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

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, String> {
    //User info DTO
    @Query("SELECT new com.joel.newsapp.dtos.users.UserInfoDTO(u.name, u.lastname, u.displayName, u.email, u.image.id AS profilePictureId, u.role, u.enabled) FROM User u WHERE u.id = :userId")
    Optional<UserInfoDTO> getUserInfoDTO(@Param("userId") String userId);
    //get all users info
    @Query("SELECT new com.joel.newsapp.dtos.users.UserInfoDTO(u.name, u.lastname, u.displayName, u.email, u.image.id AS profilePictureId, u.role, u.enabled) FROM User u WHERE u.role = 'USER'")
    List<UserInfoDTO> getAllUsers();
    @Query("SELECT user FROM User user WHERE user.email = :username")
    Optional<User> findUser(@Param("username") String email);
    /*
    @Query("SELECT NEW com.joel.eggnews.dtos.users.UserLoginDTO(u.email, u.password, u.role) FROM User u WHERE u.email = :username")
    Optional<UserLoginDTO> findByUsername(@Param("username") String username);
    @Query("SELECT NEW com.joel.eggnews.dtos.users.UserLoginDTO(u.email, u.password, u.role) FROM User u WHERE u.id = :userId")
    Optional<UserLoginDTO> infoDTOById(@Param("userId") String id);*/
    @Query("SELECT Image i from User u WHERE u.id = :userId")
    Image getImageById(@Param("userId") Long userId) throws NotFoundException;
    @Query("SELECT reporter FROM User reporter WHERE reporter.role = REPORTER")
    List<User> finAllReporters();
    @Query("SELECT reporter FROM User reporter WHERE reporter.role = ADMIN")
    List<User> finAllAdmins();
    @Query("SELECT reporter FROM User reporter WHERE reporter.role = USER")
    List<User> finAllUsers();

    Optional<User> findByEmail(String email);
}
