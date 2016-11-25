package repository.Impl;

import models.Comment;
import repository.CommentRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import database.Database;
import database.DatabaseJDBC;


public class CommentRepositoryImpl implements CommentRepository {
	private Database database = DatabaseJDBC.getInstance();
	private Connection conn = database.getConnection();

    @Override
    public List<Comment> getAllComments(int post_postId) {

        List<Comment> list = new ArrayList<>();

        try{
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("select * from comment where post_postId=\""+post_postId+"\" order by date desc");
            while(rs.next()){
                //int commentId, String date, String body, int post_postId, String user_nickname
                list.add(new Comment(Integer.parseInt(rs.getString(1)),rs.getString(2),rs.getString(3),Integer.parseInt(rs.getString(4)),rs.getString(5)));
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());

        }

        return list;
    }

    @Override
    public int createComment(String date, String body, int post_postId, String user_nickname) {
        String str = "insert into comment (date, body, post_postId, user_nickname) values (?,?,?,?)";

        int id=-1;
        PreparedStatement preparedStatement;
        //String str = "insert into post (number_of_likes, date, body, category, user_nickname, title) values (0, NOW(), ?, ?, ?, ?)";
        try {
            preparedStatement=conn.prepareStatement(str, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, date);
            preparedStatement.setInt(3, post_postId);
            preparedStatement.setString(2, body);
            preparedStatement.setString(4,user_nickname);
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if(rs.next()) {
                //In this exp, the autoKey val is in 1st col
                id=rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return id;
    }

    @Override
    public Comment getComment(int commentId) {
        Comment comment = null;

        try{
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("select * from comment where commentId="+commentId+" limit 1");
            if(!rs.isBeforeFirst()){
                return comment=null;
            }
            while(rs.next()){
                comment= new Comment(Integer.parseInt(rs.getString(1)),rs.getString(2),rs.getString(3),Integer.parseInt(rs.getString(4)),rs.getString(5));

            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());

        }


        return comment;
    }

    @Override
    public boolean updateComment(int commentId, Comment comment) {
        String str = "UPDATE comment set date=? , body=? where commentId=?";
        boolean id=false;
        PreparedStatement preparedStatement;
        //String str = "insert into post (number_of_likes, date, body, category, user_nickname, title) values (0, NOW(), ?, ?, ?, ?)";
        try {
            preparedStatement=conn.prepareStatement(str, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, comment.getDate().toString());
            preparedStatement.setInt(3, commentId);
            preparedStatement.setString(2, comment.getBody());
            id=preparedStatement.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }


        return id;
    }

    @Override
    public boolean deleteComment(int commentId) {
        String str = "delete from comment where commentId="+commentId;
        int res = getRes(str);
        return res==1;
    }

    @Override
    public List<Comment> getApprovedComments(int postId) {
        List<Comment> list = new ArrayList<>();

        try{
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("select * from comment as c where post_postId="+postId+" and c.commentId not in (select a.commentId from approval as a)");
            while(rs.next()){
                //int commentId, String date, String body, int post_postId, String user_nickname
                list.add(new Comment(Integer.parseInt(rs.getString(1)),rs.getString(2),rs.getString(3),Integer.parseInt(rs.getString(4)),rs.getString(5)));
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());

        }

        return list;
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
