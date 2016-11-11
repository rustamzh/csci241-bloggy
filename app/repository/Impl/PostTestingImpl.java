package repository.Impl;

import models.Post;
import models.User;
import repository.PostRepository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by ruszh on 08-Nov-16.
 */
public class PostTestingImpl implements PostRepository {
    List<Post> list = new ArrayList<>();
    boolean firsttime=true;
    @Override
    public List<Post> getAllPosts() {
        if(firsttime) {
            list.add(new Post(0,new Calendar.Builder().setDate(2016,10,8).build().getTime() ,"First post" ,"<p>This is the first post in this blog</p>","Uncategorized",new User("head_admin", "123","admin", "Rustam Zhumagambetov")));
            list.add(new Post(1, new Calendar.Builder().setDate(2016, 11, 8).build().getTime(), "Second post", "<p>This is the second post in this blog</p>", "Uncategorized", new User("head_admin", "123", "admin", "Rustam Zhumagambetov")));
            list.add(new Post(2, new Calendar.Builder().setDate(2016, 11, 8).build().getTime(), "Thirs post", "<p>This is the third post in this blog</p>", "Uncategorized", new User("head_admin", "123", "admin", "Rustam Zhumagambetov")));
            list.add(new Post(3, new Calendar.Builder().setDate(2016, 11, 8).build().getTime(), "Second post", "<p>This is the second post in this blog</p>", "Uncategorized", new User("head_admin", "123", "admin", "Rustam Zhumagambetov")));
            list.add(new Post(4, new Calendar.Builder().setDate(2016, 11, 8).build().getTime(), "Thirs post", "<p>This is the third post in this blog</p>", "Uncategorized", new User("head_admin", "123", "admin", "Rustam Zhumagambetov")));
            list.add(new Post(5, new Calendar.Builder().setDate(2016, 11, 8).build().getTime(), "Second post", "<p>This is the second post in this blog</p>", "Uncategorized", new User("head_admin", "123", "admin", "Rustam Zhumagambetov")));
            list.add(new Post(6, new Calendar.Builder().setDate(2016, 11, 8).build().getTime(), "Thirs post", "<p>This is the third post in this blog</p>", "Uncategorized", new User("head_admin", "123", "admin", "Rustam Zhumagambetov")));
            list.add(new Post(7, new Calendar.Builder().setDate(2016, 11, 8).build().getTime(), "Second post", "<p>This is the second post in this blog</p>", "Uncategorized", new User("head_admin", "123", "admin", "Rustam Zhumagambetov")));
            list.add(new Post(8, new Calendar.Builder().setDate(2016, 11, 8).build().getTime(), "Thirs post", "<p>This is the third post in this blog</p>", "Uncategorized", new User("head_admin", "123", "admin", "Rustam Zhumagambetov")));
            list.add(new Post(9, new Calendar.Builder().setDate(2016, 11, 8).build().getTime(), "Second post", "<p>This is the second post in this blog</p>", "Uncategorized", new User("head_admin", "123", "admin", "Rustam Zhumagambetov")));
            list.add(new Post(10, new Calendar.Builder().setDate(2016, 11, 8).build().getTime(), "Thirs post", "<p>This is the third post in this blog</p>", "Uncategorized", new User("head_admin", "123", "admin", "Rustam Zhumagambetov")));
            list.add(new Post(11, new Calendar.Builder().setDate(2016, 11, 8).build().getTime(), "Second post", "<p>This is the second post in this blog</p>", "Uncategorized", new User("head_admin", "123", "admin", "Rustam Zhumagambetov")));
            list.add(new Post(12, new Calendar.Builder().setDate(2016, 11, 8).build().getTime(), "Thirs post", "<p>This is the third post in this blog</p>", "Uncategorized", new User("head_admin", "123", "admin", "Rustam Zhumagambetov")));
            list.add(new Post(13, new Calendar.Builder().setDate(2016, 11, 8).build().getTime(), "Second post", "<p>This is the second post in this blog</p>", "Uncategorized", new User("head_admin", "123", "admin", "Rustam Zhumagambetov")));
            list.add(new Post(14, new Calendar.Builder().setDate(2016, 11, 8).build().getTime(), "Thirs post", "<p>This is the third post in this blog</p>", "Uncategorized", new User("head_admin", "123", "admin", "Rustam Zhumagambetov")));
            list.add(new Post(15, new Calendar.Builder().setDate(2016, 11, 8).build().getTime(), "Second post", "<p>This is the second post in this blog</p>", "Uncategorized", new User("head_admin", "123", "admin", "Rustam Zhumagambetov")));
            list.add(new Post(16, new Calendar.Builder().setDate(2016, 11, 8).build().getTime(), "Thirs post", "<p>This is the third post in this blog</p>", "Uncategorized", new User("head_admin", "123", "admin", "Rustam Zhumagambetov")));
            list.add(new Post(17, new Calendar.Builder().setDate(2016, 11, 8).build().getTime(), "Second post", "<p>This is the second post in this blog</p>", "Uncategorized", new User("head_admin", "123", "admin", "Rustam Zhumagambetov")));
            list.add(new Post(18, new Calendar.Builder().setDate(2016, 11, 8).build().getTime(), "Thirs post", "<p>This is the third post in this blog</p>", "Uncategorized", new User("head_admin", "123", "admin", "Rustam Zhumagambetov")));
            list.add(new Post(19, new Calendar.Builder().setDate(2016, 11, 8).build().getTime(), "Forth post", "<p>This is the forth post in this blog</p>", "Uncategorized", new User("head_admin", "123", "admin", "Rustam Zhumagambetov")));
            firsttime=false;
        }

            return list;
    }

    @Override
    //int number_of_likes, Date date,String title, String body, String category, User user
    public boolean createPost(Date date,String title, String body, String category, User user) {
        list.add(new Post(0,new Date(),title,body,"Uncategorized", user));
        return true;
    }

    @Override
    public Post getPost(int postId) {
        return null;
    }

    @Override
    public boolean updatePost(int postId, Post post) {
        for(Post p :list){
            if(p.equals(post)){
                list.remove(p);
                list.add(post);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deletePost(int postId) {
        for(Post p : list){
            if(p.getPostId()==postId && list.remove(p))
                return true;
        }
        return false;
    }

    @Override
    public List<Post> getPostsbyCatgory(String category) {
        return null;
    }

    @Override
    public List<Post> getPostbyUser(User user) {
        return null;
    }

    @Override
    public List<Post> getPostsbyDate(Date date) {
        return null;
    }

    @Override
    public List<String> getAllCategories() {
        List<String> list = new ArrayList<>();
        list.add("Uncategorized");
        list.add("Fun");
        list.add("Archive");
        list.add("Pictures");
        return list;
    }

    @Override
    public boolean deleteAllPosts() {
        list.clear();
        return true;
    }
}
