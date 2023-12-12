package com.joel.newsapp.services;

import com.joel.newsapp.entities.Comment;
import com.joel.newsapp.entities.CommentReaction;
import com.joel.newsapp.entities.Report;
import com.joel.newsapp.entities.User;
import com.joel.newsapp.exceptions.NotFoundException;
import com.joel.newsapp.repositories.ICommentReactionRepository;
import com.joel.newsapp.repositories.IReporterRepository;
import com.joel.newsapp.services.interfaces.ICommentReactionService;
import com.joel.newsapp.services.interfaces.ICommentService;
import com.joel.newsapp.services.interfaces.IReportRepository;
import com.joel.newsapp.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentReactionService implements ICommentReactionService {
    @Autowired private IUserService userService;
    @Autowired private ICommentReactionRepository reactionRepository;
    @Autowired private ICommentService commentService;
    @Autowired private IReportRepository reportRepository;
    @Override
    public String like(String email, String commentId, Boolean isPositive) throws NotFoundException {
        Optional<CommentReaction> reactionOptional = this.reactionRepository.findByComment_IdAndUser_Email(commentId, email);
        if (reactionOptional.isPresent()) {
            CommentReaction reaction = reactionOptional.get();
            if (isPositive == reaction.getIsPositive()) {
                this.reactionRepository.delete(reaction);
                return "Reaction deleted";
            } else {
                reaction.setIsPositive(isPositive);
                this.reactionRepository.save(reaction);
                return "Reaction changed";
            }
        } else {
            User user = this.userService.findUserByEmail(email);
            Comment comment = this.commentService.getById(commentId);
            CommentReaction newReaction = CommentReaction.builder()
                    .comment(comment)
                    .isPositive(isPositive)
                    .user(user)
                    .build();
            this.reactionRepository.save(newReaction);
            return "Reaction created";
        }
    }

    @Override
    public String report(String email, String commentId) throws NotFoundException {
        Optional<Report> reportOptional = this.reportRepository.findByComment_IdAndUser_Email(commentId, email);
        if (reportOptional.isPresent()) {
            return "Already reported";
        }
        Comment comment = this.commentService.getById(commentId);
        User user = this.userService.findUserByEmail(email);
        Report report = Report.builder()
                .comment(comment)
                .user(user)
                .build();
        this.reportRepository.save(report);
        return "Report saved";
    }
}
