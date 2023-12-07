package com.joel.newsapp.controllers;

import com.joel.newsapp.dtos.comment.CommentPostReqDTO;
import com.joel.newsapp.entities.Comment;
import com.joel.newsapp.exceptions.NotFoundException;
import com.joel.newsapp.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/add/{category}/{id}")
    public String addComment(@PathVariable String category, @PathVariable String id, @RequestParam String comment, ModelMap model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String user = auth.getName();
        CommentPostReqDTO commentDTO = new CommentPostReqDTO(comment, id, user);
        try {
            Comment newComment = this.commentService.save(commentDTO);
            model.addAttribute("commentOk", "Comentario agregado");
        } catch (NotFoundException e) {
            model.addAttribute("commentError", e.getMessage());
        }
        return "redirect:/news/" + category + "/" + id;
    }
}
