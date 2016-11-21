package repository.Impl;

import models.Comment;
import repository.CommentRepository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
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
            ResultSet rs = stm.executeQuery("select * from comment");
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
    public boolean createComment(String date, String body, int post_postId, String user_nickname) {
        String str = "insert into comment (date, body, post_postId, user_nickname) values ('"+ Date.valueOf(date).toString()+"','"+body+"', "+post_postId+",'" + user_nickname + "')";
        int res = getRes(str);
        return res==1;
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
        String str = "UPDATE comment set date=\""+Date.valueOf(comment.getDate()).toString()+"\" , body=\""+comment.getBody()+"\" where commentId="+commentId;
        int res = getRes(str);
        return res==1;
    }

    @Override
    public boolean deleteComment(int commentId) {
        String str = "delete from comment where commentId="+commentId;
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
