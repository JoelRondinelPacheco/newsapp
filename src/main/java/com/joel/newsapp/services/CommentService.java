package com.joel.newsapp.services;

import com.joel.newsapp.dtos.comment.*;
import com.joel.newsapp.entities.*;
import com.joel.newsapp.exceptions.NotFoundException;
import com.joel.newsapp.repositories.ICommentRepository;
import com.joel.newsapp.repositories.INewsRepository;
import com.joel.newsapp.services.interfaces.ICommentService;
import com.joel.newsapp.utils.BuildDTOs;
import jakarta.transaction.Transactional;
import org.hibernate.annotations.DialectOverride;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Comment getById(String id) throws NotFoundException {
        Optional<Comment> commentOptional = this.commentRepository.findById(id);
        if (commentOptional.isPresent()) {
            return commentOptional.get();
        }
        throw new NotFoundException("Comment not found");
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

    @Override
    public CommentDashboardPageDTO getByReports(int pageNumber, int pageSize) {
        Pageable page = PageRequest.of(pageNumber - 1, pageSize);
        Page<Comment> commentsPage = this.commentRepository.selectByReports(page);
        return CommentDashboardPageDTO.builder()
                .comments(this.dto.commentDashboardDTOList(commentsPage.getContent()))
                .totalPages(commentsPage.getTotalPages())
                .totalElements(commentsPage.getTotalElements())
                .build();
    }

}
