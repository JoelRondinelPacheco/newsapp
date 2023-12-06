package com.joel.newsapp.utils;

import com.joel.newsapp.dtos.comment.CommentViewDTO;
import com.joel.newsapp.dtos.news.NewsHomeDTO;
import com.joel.newsapp.dtos.newscategory.CategoryDTO;
import com.joel.newsapp.dtos.reporter.ReporterInfoDTO;
import com.joel.newsapp.dtos.users.Employee;
import com.joel.newsapp.dtos.users.EmployeeDTO;
import com.joel.newsapp.dtos.users.UserInfoDTO;
import com.joel.newsapp.dtos.users.UserProfileInfoDTO;
import com.joel.newsapp.entities.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class BuildDTOs {

    public UserInfoDTO createUserInfoDTO(User user) {
        UserInfoDTO userInfo = UserInfoDTO.builder()
                .name(user.getName())
                .lastname(user.getLastname())
                .displayName(user.getDisplayName())
                .email(user.getEmail())
                .role(user.getRole())
                .enabled(user.getEnabled())
                .id(user.getId())
                .build();
        if (user.getImage() == null) {
            userInfo.setProfilePictureId("user_img");
        } else {
            userInfo.setProfilePictureId(user.getImage().getId());
        }
        return userInfo;
    }
    public List<UserInfoDTO> listUserInfoDTO(List<User> users) {
        List<UserInfoDTO> usersDTO = new ArrayList<>();
        for(User u : users) {
            usersDTO.add(this.createUserInfoDTO(u));
        }
        return usersDTO;
    }

    public UserProfileInfoDTO createUserProfileInfo(User user) {
        UserProfileInfoDTO userDTO = new UserProfileInfoDTO(user.getName(), user.getLastname(), user.getDisplayName(), user.getEmail());
        if (user.getImage() == null) {
            userDTO.setProfilePictureId("user_image");
        } else {
            userDTO.setProfilePictureId(user.getImage().getId());
        }
        return userDTO;
    }

    public EmployeeDTO createEmployeeInfo(Employee employee) {
        User user = employee.getUser();
        UserInfoDTO userInfo = UserInfoDTO.builder()
                .name(user.getName())
                .lastname(user.getLastname())
                .displayName(user.getDisplayName())
                .email(user.getEmail())
                .role(user.getRole())
                .enabled(user.getEnabled())
                .id(user.getId())
                .build();
        if (user.getImage() == null) {
            userInfo.setProfilePictureId("user_img");
        } else {
            userInfo.setProfilePictureId(user.getImage().getId());
        }
        return new EmployeeDTO(employee.getMonthlySalary(), employee.getEnabled(), employee.getId(), userInfo);
    }

    public List<CategoryDTO> createListCategoryDTO(List<NewsCategory> categories) {
        List<CategoryDTO> dto = new ArrayList<>();
        for (NewsCategory c : categories) {
            dto.add(
                    CategoryDTO.builder()
                            .name(c.getName())
                            .id(c.getId())
                            .selected(false)
                    .build()
        );
        }
        return dto;
    }

    public NewsHomeDTO createNewsHomeDTO(News news) {
        String[] time = this.hourAndDate(news.getCreatedAt());
        return NewsHomeDTO.builder()
                .newsId(news.getId())
                .newsTitle(news.getTitle())
                .newsDate(time[0])
                .newsHour(time[1])
                .newsSubtitle(news.getSubtitle())
                .newsCategory(news.getMainCategory().getName())
                .reporterId(news.getAuthor().getUser().getId())
                .reporterName(news.getAuthor().getUser().getName() + " " + news.getAuthor().getUser().getLastname())
                .reporterEmail(news.getAuthor().getUser().getEmail())
                .build();

    }

    public List<NewsHomeDTO> createListNewsHomeDTO(List<News> news) {
        List<NewsHomeDTO> dtos = new ArrayList<>();
        for (News n : news) {
            dtos.add(this.createNewsHomeDTO(n));
        }
        return dtos;
    }

    public CommentViewDTO commentViewDTO(Comment comment) {
        String[] time = this.hourAndDate(comment.getCreatedAt());
        int positiveScore = 0;
        int negativeScore = 0;
        for (CommentReaction c : comment.getReactions()) {
            if (c.getIsPositive()) {
                positiveScore += 1;
            } else {
                negativeScore += 1;
            }
        }
        return CommentViewDTO.builder()
                .authorId(comment.getAuthorComment().getId())
                .authorName(comment.getAuthorComment().getDisplayName())
                .comment(comment.getComment())
                .positiveScore(positiveScore)
                .negativeScore(negativeScore)
                .date(time[0])
                .hour(time[1])
                .build();
    }

    public List<CommentViewDTO> commentViewDTOList (List<Comment> comments) {
        List<CommentViewDTO> dto = new ArrayList<>();
        for (Comment c : comments) {
            dto.add(this.commentViewDTO(c));
        }
        return dto;
    }

    public String[] hourAndDate(LocalDateTime date) {
        String[] time = new String[2];
        time[0] = date.toString().split("T")[0];
        time[1] = date.toString().split("T")[1].substring(0,5);
        return time;
    }
}
