package repository;

import models.Comment;

import java.util.List;


public interface CommentRepository {
    List<Comment> getAllComments(int post_postId);
    boolean createComment(String date, String body, int post_postId, String user_nickname);
    Comment getComment(int commentId);
    boolean updateComment(int commentId, Comment comment);
    boolean deleteComment(int commentId);
    List<Comment> getApprovedComments(int postId);
}
