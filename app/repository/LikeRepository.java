package repository;

import models.Like;

import java.util.List;

/**
 * Created by ruszh on 06-Nov-16.
 */
public interface LikeRepository {
    List<Like> getAllLikes();
    boolean createLike(String user_nickname, int post_postId);
    boolean deleteLike(String user_nickname, int post_postId);
}
