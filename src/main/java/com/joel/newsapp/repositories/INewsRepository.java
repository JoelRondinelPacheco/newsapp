package com.joel.newsapp.repositories;

import com.joel.newsapp.entities.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface INewsRepository extends JpaRepository<News, String> {
    List<News> findByAuthor_User_email(String authorId);
    Optional<News> findByMainFeatured(boolean featured);
    List<News> findByMainCategory_Id(String id, Pageable page);
/*
NO SE PUEDE USAR LIMIT, IMPLEMENTAR PAGEABLE
    @Query("SELECT n FROM News n JOIN n.categories c WHERE c.category =:category LIMIT :limit")
    List<News> findByCategory(@Param("category") String category, @Param("limit") int limit);
*/

    List<News> findAllByOrderByCreatedAtAsc();

    List<News> findByFeaturedCategory(Boolean b);

    List<News> findByFeaturedCategoryAndMainCategory_Name(boolean b, String category);

    List<News> findByAuthor_User_NameAndAuthor_User_Lastname(String name, String lastname);
    List<News> findByAuthor_User_NameAndAuthor_User_LastnameAndTitle(String name, String lastname, String title);
    //TODO by date List<News> findByAuthor_User_NameAndAuthor_User_LastnameAndTitleAndCreatedAt(String name, String lastname, String title, );
    List<News> findByTitle(String title);

    @Query(value = "SELECT * FROM news_table n WHERE DATE(n.created_at) = :date",
            nativeQuery = true)
    List<News> findByDate(@Param("date") String dateF);

    @Query(value = "SELECT n.* FROM news_table n " +
            "LEFT JOIN reporters r ON n.author_id = r.id " +
            "JOIN users u ON r.user_id = u.id " +
            "WHERE CONCAT(LOWER(u.name), ' ', LOWER(u.lastname)) = :reporterName", nativeQuery = true)
            //"(SELECT u.id FROM users u WHERE CONCAT(u.name, ' ', u.lastname) = :reporterName)", nativeQuery = true)
    List<News> findByReporterName(@Param("reporterName") String name);

    @Query(value = "SELECT n.* FROM news_table n " +
            "LEFT JOIN reporters r ON n.author_id = r.id " +
            "JOIN users u ON r.user_id = u.id " +
            "WHERE CONCAT(LOWER(u.name), ' ', LOWER(u.lastname)) = :reporterName AND n.title = :title", nativeQuery = true)
        //"(SELECT u.id FROM users u WHERE CONCAT(u.name, ' ', u.lastname) = :reporterName)", nativeQuery = true)
    List<News> findByReporterNameAndNewsTitle(@Param("reporterName") String name, @Param("title") String title);

}
