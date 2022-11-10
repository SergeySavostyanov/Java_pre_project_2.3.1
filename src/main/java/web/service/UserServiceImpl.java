package web.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    @Override
    public List<User> listUsers() {
        return dao.listUsers();
    }
    @Override
    @Transactional
    public void saveUser(User user) {
        dao.saveUser(user);
    }
    @Override
    @Transactional
    public void removeUser(int id) {
        dao.removeUser(id);
    }
    @Override
    @Transactional
    public void updateUser(User user) {
        dao.updateUser(user);
    }
}

