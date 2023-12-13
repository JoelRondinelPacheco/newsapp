package com.joel.newsapp.controllers;

import com.joel.newsapp.dtos.comment.CommentPostReqDTO;
import com.joel.newsapp.entities.Comment;
import com.joel.newsapp.exceptions.NotFoundException;
import com.joel.newsapp.services.CommentService;
import com.joel.newsapp.services.interfaces.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private ICommentService commentService;

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

    @GetMapping("/delete/{commentId}")
    public String deleteComment(@PathVariable String commentId, @RequestParam(required = false) Integer current_page, @RequestParam(required = false) Integer page_size, ModelMap model) {
        if (current_page == null) {
            current_page = 1;
        }
        if (page_size == null) {
            page_size = 10;
        }
        try {
            this.commentService.delete(commentId);
            return "redirect:/dashboard/comments?current_page=" + current_page + "&page_size=" + page_size;
        } catch (NotFoundException e) {
            return "redirect:/dashboard/comments?current_page=" + current_page + "&page_size=" + page_size;

        }
    }
}
