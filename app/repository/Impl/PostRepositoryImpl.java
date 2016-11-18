package repository.Impl;

import database.Database;
import database.DatabaseJDBC;
import models.Post;
import repository.PostRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class PostRepositoryImpl implements PostRepository{
	
    private Database database = DatabaseJDBC.getInstance();
	private Connection conn = database.getConnection();

    @Override
    public List<Post> getAllPosts() {
        List<Post> list = new ArrayList<>();
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("select postId, number_of_likes, date, body, category, user_nickname, title from post ORDER BY date DESC ");
            while(rs.next()){
                //int approvalId, int commentId
                //System.out.println(Integer.parseInt(rs.getString(1))+" "+ Integer.parseInt(rs.getString(2))+" "+ java.sql.Date.valueOf(rs.getString(3).substring(0,10))+" "+rs.getString(7)+" "+rs.getString(4)+""+rs.getString(5)+" "+rs.getString(6));
                list.add(new Post(Integer.parseInt(rs.getString(1)), Integer.parseInt(rs.getString(2)), java.sql.Date.valueOf(rs.getString(3).substring(0,10)),rs.getString(7) ,rs.getString(4),rs.getString(5),rs.getString(6)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        list.add(new Post(0, 3,new Calendar.Builder().setDate(2016,10,8).build().getTime() ,"First post" ,"<p>This is the first post in this blog</p>","Uncategorized","head_admin"));
        return list;
    }

    @Override
    public boolean createPost(Date date, String title, String body, String category, String user) {
        String str = "insert into post (number_of_likes, date, body, category, user_nickname, title) values (0, \""+  new SimpleDateFormat("MM/dd/yyyy").format(date)+"\",\""+body+"\", \""+category+"\", \""+user+"\",\""+title+"\" )";
        int res = getRes(str);
        return res==1;
    }

    @Override
    public Post getPost(int postId) {
        Post post = null;

        try{
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("select postId, number_of_likes, date, body, category, user_nickname, title from post where postId="+postId+" limit 1");
            if(!rs.isBeforeFirst()){
                return post=null;
            }
            while(rs.next()){
                post= (new Post(Integer.parseInt(rs.getString(1)), Integer.parseInt(rs.getString(2)), java.sql.Date.valueOf(rs.getString(3)),rs.getString(7) ,rs.getString(4),rs.getString(5),rs.getString(6)));

            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());

        }


        return post;
    }

    @Override
    public boolean updatePost(int postId, Post post) {
        String str = "UPDATE post set title=\""+post.getTitle()+"\" , body=\""+post.getBody()+"\", category=\"" + post.getCategory()+ "\", date=\""+new SimpleDateFormat().format(post.getDate())+"\" where nickname="+postId;
        int res = getRes(str);
        return res==1;
    }

    @Override
    public boolean deletePost(int postId) {
        String str = "delete from post where postId="+postId;
        int res = getRes(str);
        return res==1;
    }

    @Override
    public List<Post> getPostsbyCatgory(String category) {
        List<Post> list = new ArrayList<>();

        try{
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("select postId, number_of_likes, date, body, category, user_nickname, title from post where category=\""+category+"\" ORDER BY date DESC ");
            while(rs.next()){
                //int approvalId, int commentId
                list.add(new Post(Integer.parseInt(rs.getString(1)), Integer.parseInt(rs.getString(2)), java.sql.Date.valueOf(rs.getString(3)),rs.getString(7) ,rs.getString(4),rs.getString(5),rs.getString(6)));
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());

        }

        return list;
    }

    @Override
    public List<Post> getPostbyUser(String user) {
        List<Post> list = new ArrayList<>();

        try{
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("select postId, number_of_likes, date, body, category, user_nickname, title from post where user_nickname=\""+user+"\" ORDER BY date DESC ");
            while(rs.next()){
                //int approvalId, int commentId
                list.add(new Post(Integer.parseInt(rs.getString(1)), Integer.parseInt(rs.getString(2)), java.sql.Date.valueOf(rs.getString(3)),rs.getString(7) ,rs.getString(4),rs.getString(5),rs.getString(6)));
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());

        }

        return list;
    }

    @Override
    public List<Post> getPostsbyDate(Date date) {
        List<Post> list = new ArrayList<>();

        try{
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("select postId, number_of_likes, date, body, category, user_nickname, title from post where date="+new SimpleDateFormat().format(date));
            while(rs.next()){
                //int approvalId, int commentId
                list.add(new Post(Integer.parseInt(rs.getString(1)), Integer.parseInt(rs.getString(2)), java.sql.Date.valueOf(rs.getString(3)),rs.getString(7) ,rs.getString(4),rs.getString(5),rs.getString(6)));
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());

        }

        return list;
    }

    @Override
    public List<String> getAllCategories() {
        List<String> list = new ArrayList<>();

        try{
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("select DISTINCT category from post");
            while(rs.next()){

                list.add(rs.getString(1));
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());

        }

        return list;
    }

    @Override
    public boolean deleteAllPosts() {
        String str = "delete from post";
        int res = getRes(str);
        return res==1;
    }
    private int getRes(String str) {
        int res=0;
        Statement stm;
        try{
            stm = conn.createStatement();
            res=stm.executeUpdate(str);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        return res;
    }
}
