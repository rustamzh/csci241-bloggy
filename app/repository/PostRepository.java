package repository;

import models.Post;
import models.User;

import java.util.Date;
import java.util.List;

public interface PostRepository {
    List<Post> getAllPosts();
    boolean createPost(Date date,String title, String body, String category, User user);
    Post getPost(int postId);
    boolean updatePost(int postId, Post post);
    boolean deletePost(int postId);
    List<Post> getPostsbyCatgory(String category);
    List<Post> getPostbyUser(User user);
    List<Post> getPostsbyDate(Date date);
    List<String> getAllCategories();
}
