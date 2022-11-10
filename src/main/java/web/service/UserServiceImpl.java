package web.service;

import org.springframework.stereotype.Service;
import web.dao.UserDao;
import web.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao dao;

    public UserServiceImpl(UserDao dao) {
        this.dao = dao;
    }

    @Override
    public User getById(int id) {
        return dao.getById(id);
    }

    public List<User> listUsers() {
        return dao.listUsers();
    }

    public void saveUser(User user) {
        dao.saveUser(user);
    }

    public void removeUser(int id) {
        dao.removeUser(id);
    }

    public void updateUser(User user) {
        dao.updateUser(user);
    }
}

