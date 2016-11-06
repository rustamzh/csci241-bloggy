package repository;

import models.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class UserRepositoryImpl implements UserRepository {
    private Connection conn=null;

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();

            try{
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery("select * from user");
                while(rs.next()){
                    //String nickname, String name, String password, String type, String email, String Avatar
                    list.add(new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5), rs.getString(6)));
                }
            }catch (Exception ex){
                System.out.println(ex.getMessage());

            }

        return list;
    }

    @Override
    public boolean createUser(String nickname, String password, String type, String name) {
        String str = "insert into user (nickname, password, type, name) values (\""+nickname+"\",\""+password+"\",\""+type+"\",\"" + name + "\")";
        int res = getRes(str);
        return res==1;
    }

    @Override
    public User getUser(String nickname) {
        User user = null;

            try{
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery("select * from user where nickname=\""+nickname+"\" limit 1");
                if(!rs.isBeforeFirst()){
                    return user=null;
                }
                while(rs.next()){
                    user= new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5), rs.getString(6));

                }
            }catch (Exception ex){
                System.out.println(ex.getMessage());

            }


        return user;
    }

    @Override
    public boolean updateUser(String nickname, User user) {

        String str = "UPDATE user set name=\""+user.getName()+"\" , email=\""+user.getEmail()+"\", avatar=\"" + user.getAvatar()+ "\", password=\""+user.getPassword()+"\" where nickname=\""+nickname+"\"";
        int res = getRes(str);
        return res==1;
    }

    @Override
    public boolean deleteUser(String nickname) {

        String str = "delete from user where nickname=\""+nickname+"\"";
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
