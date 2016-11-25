package repository.Impl;

import models.User;
import repository.UserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import database.Database;
import database.DatabaseJDBC;


public class UserRepositoryImpl implements UserRepository {
	private Database database = DatabaseJDBC.getInstance();
	private Connection conn = database.getConnection();

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
    public boolean createUser(String nickname, String password, String type, String name, String email, String avatar) {
        String str = "insert into user (nickname, password, type, name, email, avatar) values (?,?,?,?,?,?)";
        int res=0;
        PreparedStatement preparedStatement;
        try {
            preparedStatement=conn.prepareStatement(str);
            preparedStatement.setString(1, nickname);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, type);
            preparedStatement.setString(4,name);
            preparedStatement.setString(5,email);
            preparedStatement.setString(6,avatar);
            res=preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
