package com.joel.newsapp.services;

import com.joel.newsapp.dtos.comment.CommentEditReqDTO;
import com.joel.newsapp.dtos.comment.CommentPostReqDTO;
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
public class CommentService implements ICrudService<Comment, CommentPostReqDTO, CommentEditReqDTO, String> {
    @Autowired
    private ICommentRepository commentRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private NewsService newsService;

    @Transactional
    @Override
    public Comment save(CommentPostReqDTO comment) {
        // TODO AGREGAR VERIFICACION DEL USUARIO QUE REALIZA LA PETICION

        try {
            News noticia = this.newsService.getById(comment.getNews_id());
            User autor = this.userService.findByEmail(comment.getUser_email());
            Comment newComment = new Comment(comment.getComment(), noticia, autor);
            return this.commentRepository.save(newComment);
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Comment getById(String s) throws NotFoundException {
        return null;
    }

    @Override
    public Comment edit(CommentEditReqDTO commentEditReqDTO) throws Exception {
        return null;
    }

    @Override
    public String deleteById(String s) {
        return null;
    }


    public List<Comment> getAllNewsComments(String newsId) throws NotFoundException {
        News news = this.newsService.getById(newsId);
        List<Comment> comments = this.commentRepository.getNewsComments(news);

        return comments;
    }
}
