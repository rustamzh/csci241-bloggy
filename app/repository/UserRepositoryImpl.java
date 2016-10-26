package repository;

import models.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class UserRepositoryImpl implements UserRepository {
    Connection conn=null;

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
    public boolean createUser(String nickname, String password, String type, String email) {
        return false;
    }

    @Override
    public User getUser(String nickname) {
        return null;
    }

    @Override
    public boolean updateUser(String nickname, User user) {
        return false;
    }

    @Override
    public boolean deleteUser(String nickname) {
        return false;
    }
}
