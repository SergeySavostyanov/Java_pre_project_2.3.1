package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {

    User getById(int id);

    List<User> listUsers();

    void saveUser(User user);

    void removeUser(int id);

    void updateUser(User user);

}
