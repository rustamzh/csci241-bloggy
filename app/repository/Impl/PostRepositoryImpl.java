package repository.Impl;

import models.Post;
import repository.PostRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by ruszh on 12-Nov-16.
 */
public class PostRepositoryImpl implements PostRepository{
    Connection conn;

    @Override
    public List<Post> getAllPosts() {
        List<Post> list = new ArrayList<>();

        try{
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("select a.approvalId, a.commentId, p.title, c.user_nickname, c.body from (approval as a join comment as c on a.commentId=c.commentId) join post as p on c.post_postId=p.postid ");
            while(rs.next()){
                //int approvalId, int commentId
                list.add(new Post(0, 3,new Calendar.Builder().setDate(2016,10,8).build().getTime() ,"First post" ,"<p>This is the first post in this blog</p>","Uncategorized","head_admin"));
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());

        }

        return list;
    }

    @Override
    public boolean createPost(Date date, String title, String body, String category, String user) {
        return false;
    }

    @Override
    public Post getPost(int postId) {
        return null;
    }

    @Override
    public boolean updatePost(int postId, Post post) {
        return false;
    }

    @Override
    public boolean deletePost(int postId) {
        return false;
    }

    @Override
    public List<Post> getPostsbyCatgory(String category) {
        return null;
    }

    @Override
    public List<Post> getPostbyUser(String user) {
        return null;
    }

    @Override
    public List<Post> getPostsbyDate(Date date) {
        return null;
    }

    @Override
    public List<String> getAllCategories() {
        return null;
    }

    @Override
    public boolean deleteAllPosts() {
        return false;
    }
}
