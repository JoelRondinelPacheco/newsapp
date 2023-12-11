package com.joel.newsapp.controllers;

import com.joel.newsapp.services.interfaces.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment-reaction")
public class CommentReactionController {

    @Autowired private ICommentService commentService;

    @GetMapping("/like/{commentId}")
    public ResponseEntity<Boolean> like(@PathVariable String commentId, @RequestParam Boolean isPositive) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        Boolean reaction  = this.commentService.like(email, commentId, isPositive);

    }
}
