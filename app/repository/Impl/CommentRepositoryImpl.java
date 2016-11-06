package repository.Impl;

import models.Comment;
import repository.CommentRepository;

import java.util.List;

/**
 * Created by ruszh on 06-Nov-16.
 */
public class CommentRepositoryImpl implements CommentRepository {
    @Override
    public List<Comment> getAllComments(int post_postId) {
        return null;
    }

    @Override
    public boolean createComment(String date, String body, int post_postId, String user_nickname) {
        return false;
    }

    @Override
    public Comment getComment(int commentId) {
        return null;
    }

    @Override
    public boolean updateComment(int commentId, Comment comment) {
        return false;
    }

    @Override
    public boolean deleteComment(int commentId) {
        return false;
    }
}
