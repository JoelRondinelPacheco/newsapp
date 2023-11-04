package com.joel.newsapp.controllers;

import com.joel.newsapp.entities.Comment;
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

    @PostMapping("/add/{id}")
    public String addComment(@PathVariable Long id, @RequestParam String comment, ModelMap model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String user = auth.getName();
        Comment newComment = this.commentService.save(comment, id, user);
        model.put("commentOk", "Comentario agregado");
        return "index.html";
    }
}
