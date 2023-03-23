package com.rm.habr.service;

import com.rm.habr.model.AdminComment;
import com.rm.habr.model.AdminCommentsPage;
import com.rm.habr.model.Comment;
import com.rm.habr.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public AdminCommentsPage getAllAdminComments(Integer page) {
        List<AdminComment> comments = commentRepository.findAllComments(page);
        return new AdminCommentsPage(comments, commentRepository.getCommentsCount());
    }

    public void deleteById(Long commentId) {
        commentRepository.delete(commentId);
    }

    public List<Comment> findCommentsByPublicationId(long publicationId) {
        return commentRepository.findCommentsByPublicationId(publicationId);
    }

    public int getRowsCount() {
        return commentRepository.getCommentsCount();
    }

}
