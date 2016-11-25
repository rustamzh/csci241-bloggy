package repository.Impl;

import models.Like;
import repository.LikeRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class LikeRepositoryImpl implements LikeRepository {

    private Connection conn=null;

    @Override
    public List<Like> getAllLikes() {
        List<Like> list = new ArrayList<>();

        try{
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("select * from user_likes_post");
            while(rs.next()){
                //int post_postId, String user_nickname
                list.add(new Like(Integer.parseInt(rs.getString(1)),rs.getString(2)));
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());

        }

        return list;
    }

    @Override
    public boolean createLike(String user_nickname, int post_postId) {
        String str = "insert into user_likes_post (user_nickname, post_postId) values (\""+ user_nickname+"\","+post_postId+")";
        int res = getRes(str);
        return res==1;
    }

    @Override
    public boolean deleteLike(String user_nickname, int post_postId) {
        String str = "delete from user_likes_post where user_nickname=\""+user_nickname+"\" and post_postId="+post_postId;
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
