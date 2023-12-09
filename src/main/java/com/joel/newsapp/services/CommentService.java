package com.joel.newsapp.services;

import com.joel.newsapp.dtos.comment.CommentEditReqDTO;
import com.joel.newsapp.dtos.comment.CommentPostReqDTO;
import com.joel.newsapp.dtos.comment.CommentViewDTO;
import com.joel.newsapp.entities.Comment;
import com.joel.newsapp.entities.News;
import com.joel.newsapp.entities.Report;
import com.joel.newsapp.entities.User;
import com.joel.newsapp.exceptions.NotFoundException;
import com.joel.newsapp.repositories.ICommentRepository;
import com.joel.newsapp.repositories.INewsRepository;
import com.joel.newsapp.services.interfaces.ICommentService;
import com.joel.newsapp.utils.BuildDTOs;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService implements ICommentService {
    @Autowired private ICommentRepository commentRepository;
    @Autowired private UserService userService;
    @Autowired private NewsService newsService;
    @Autowired private BuildDTOs dto;

    @Transactional
    public Comment save(CommentPostReqDTO comment) throws NotFoundException{
        // TODO AGREGAR VERIFICACION DEL USUARIO QUE REALIZA LA PETICION
        try {
            News noticia = this.newsService.getById(comment.getNews_id());
            User autor = this.userService.findUserByEmail(comment.getUser_email());
            Comment newComment = new Comment(comment.getComment(), noticia, autor);
            return this.commentRepository.save(newComment);
        } catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    public Comment getById(String s) throws NotFoundException {
        return null;
    }

    public Comment edit(CommentEditReqDTO commentEditReqDTO) throws Exception {
        return null;
    }

    public String deleteById(String s) {
        return null;
    }
    @Override
    public List<CommentViewDTO> getAllNewsComments(String newsId, String email) throws NotFoundException {
        this.newsService.existsById(newsId);
        List<Comment> comments = this.commentRepository.findAllByNews_Id(newsId);
        List<CommentViewDTO> dto = this.dto.commentViewDTOList(comments, email);
        return dto;
    }
}
