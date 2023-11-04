package com.joel.newsapp.services;

import com.joel.newsapp.entities.Comment;
import com.joel.newsapp.entities.News;
import com.joel.newsapp.entities.User;
import com.joel.newsapp.exceptions.NotFoundException;
import com.joel.newsapp.repositories.ICommentRepository;
import com.joel.newsapp.services.interfaces.ICrudService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService implements ICrudService<Comment,> {
    @Autowired
    private ICommentRepository commentRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private NewsService newsService;
@Transactional
    public Comment save(String comment, Long news_id, String user){
        // TODO AGREGAR VERIFICACION DEL USUARIO QUE REALIZA LA PETICION

        try {
            News noticia = this.newsService.getById(news_id);
            User autor = this.userService.findByEmail(user);
            Comment newComment = new Comment(comment, noticia, autor);
            return this.commentRepository.save(newComment);
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Comment> getAllNewsComments(Long newsId) throws NotFoundException {
        News news = this.newsService.getById(newsId);
    List<Comment> comments = this.commentRepository.getNewsComments(news);

    return comments;
    }
}
