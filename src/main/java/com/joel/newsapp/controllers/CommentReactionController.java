package com.joel.newsapp.controllers;

import com.joel.newsapp.exceptions.NotFoundException;
import com.joel.newsapp.services.interfaces.ICommentReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment-reaction")
public class CommentReactionController {

    @Autowired private ICommentReactionService reactionService;

    @GetMapping("/like/{commentId}")
    public ResponseEntity<String> like(@PathVariable String commentId, @RequestParam Boolean is_positive) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        if (!email.equals("anonymousUser")) {
            try {
                String reaction = this.reactionService.like(email, commentId, is_positive);
                System.out.println(reaction);
                return new ResponseEntity<>(reaction, httpHeaders,HttpStatus.OK);
            } catch (NotFoundException e) {
                System.out.println(e.getMessage());
                return new ResponseEntity<>(e.getMessage(), httpHeaders,HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("User not authenticated", httpHeaders, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/report/{commentId}")
    public ResponseEntity<String> report(@PathVariable String commentId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        if (!email.equals("anonymousUser")) {
            try {
                String report = this.reactionService.report(email, commentId);
                return new ResponseEntity<>(report, HttpStatus.OK);
            } catch (NotFoundException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("User not authenticated", HttpStatus.NOT_FOUND);
        }
    }
}
