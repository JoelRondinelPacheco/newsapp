package com.joel.newsapp.utils;

import com.joel.newsapp.dtos.comment.CommentViewDTO;
import com.joel.newsapp.dtos.news.NewsForm;
import com.joel.newsapp.dtos.news.NewsHomeDTO;
import com.joel.newsapp.dtos.news.NewsPostReqDTO;
import com.joel.newsapp.dtos.news.NewsViewDTO;
import com.joel.newsapp.dtos.newscategory.CategoriesFormDTO;
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
        UserProfileInfoDTO userDTO = new UserProfileInfoDTO(user.getId(), user.getName(), user.getLastname(), user.getDisplayName(), user.getEmail());
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

    public NewsHomeDTO newsHomeDTO(News news) {
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
                .newsImageId(news.getImage().getId())
                .build();

    }

    public NewsViewDTO newsViewDTO(News news) {
        NewsHomeDTO dto = this.newsHomeDTO(news);
        return NewsViewDTO.builder()
                .newsImage(news.getImage().getId())
                .newsImageCaption(news.getImageCaption())
                .newsBody(news.getBody())
                .newsCategoryId(news.getMainCategory().getId())
                .newsDetails(dto)
                .build();
    }

    public List<NewsHomeDTO> newsHomeDTOList(List<News> news) {
        List<NewsHomeDTO> dto = new ArrayList<>();
        for (News n : news) {
            dto.add(this.newsHomeDTO(n));
        }
        return dto;
    }

    public CommentViewDTO commentViewDTO(Comment comment, String email) {
        String[] time = this.hourAndDate(comment.getCreatedAt());

        CommentViewDTO commentDTO = CommentViewDTO.builder()
                .authorId(comment.getAuthorComment().getId())
                .authorName(comment.getAuthorComment().getDisplayName())
                .comment(comment.getComment())
                .date(time[0])
                .hour(time[1])
                .user(!email.equals("anonymousUser"))
                .build();

        for (CommentReaction c : comment.getReactions()) {
            if (!email.equals("anonymousUser")) {
                String userEmail = c.getUser().getEmail();
                if (userEmail.equalsIgnoreCase(email)) {
                    commentDTO.setLike(c.getIsPositive());
                    commentDTO.setDislike(!c.getIsPositive());
                }
            } else {
                commentDTO.setLike(false);
                commentDTO.setDislike(false);
            }
        }


        if (!email.equals("anonymousUser")) {
            for (Report r : comment.getReports()) {
                commentDTO.setReported(r.getUser().getEmail().equalsIgnoreCase(email));
            }
        } else {
            commentDTO.setReported(false);
        }
            return commentDTO;
    }

    public List<CommentViewDTO> commentViewDTOList (List<Comment> comments, String email) {
        List<CommentViewDTO> dto = new ArrayList<>();
        for (Comment c : comments) {
            dto.add(this.commentViewDTO(c, email));
        }
        return dto;
    }

    public NewsForm newsForm(News news, List<CategoriesFormDTO> categoriesDTO) {
        if (news != null) {
            return NewsForm.builder()
                    .id(news.getId())
                    .title(news.getTitle())
                    .subtitle(news.getSubtitle())
                    .imageCaption(news.getImageCaption())
                    .body(news.getBody())
                    .categories(categoriesDTO)
                    .mainCategoryId(news.getMainCategory().getId())
                    .reporterUsername(news.getAuthor().getUser().getEmail())
                    .imageId(news.getImage().getId())
                    .build();
        } else {
            return NewsForm.builder()
                    .id("")
                    .title("")
                    .subtitle("")
                    .imageCaption("")
                    .body("")
                    .categories(categoriesDTO)
                    .mainCategoryId("")
                    .reporterUsername("")
                    .imageId("")
                    .build();
        }
    }

    public String[] hourAndDate(LocalDateTime date) {
        String[] time = new String[2];
        time[0] = date.toString().split("T")[0];
        time[1] = date.toString().split("T")[1].substring(0,5);
        return time;
    }
}
