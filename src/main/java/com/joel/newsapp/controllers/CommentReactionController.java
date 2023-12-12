package com.joel.newsapp.controllers;

import com.joel.newsapp.exceptions.NotFoundException;
import com.joel.newsapp.services.interfaces.ICommentReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment-reaction")
public class CommentReactionController {

    @Autowired private ICommentReactionService reactionService;

    @GetMapping("/like/{commentId}")
    public ResponseEntity<String> like(@PathVariable String commentId, @RequestParam Boolean isPositive) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        try {
            String reaction  = this.reactionService.like(email, commentId, isPositive);
            return new ResponseEntity<>(reaction, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/report/{commentId}")
    public ResponseEntity<String> report(@PathVariable String commentId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        try {
            String report = this.reactionService.report(email, commentId);
            return new ResponseEntity<>(report, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
