package repository;

import models.User;

import java.util.List;


public interface UserRepository {
    List<User> getAllUsers();
    boolean createUser(String nickname, String password, String type, String name, String email, String avatar);
    User getUser(String nickname);
    boolean updateUser(String nickname, User user);
    boolean deleteUser(String nickname);
}
