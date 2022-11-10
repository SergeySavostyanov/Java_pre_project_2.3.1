package web.dao;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User getById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    @Transactional
    public List<User> listUsers() {
        TypedQuery<User> query = entityManager.createQuery("from User", User.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        entityManager.merge(user);
    }

    @Override
    @Transactional
    public void removeUser(int id) {
        entityManager.remove(entityManager.find(User.class, id));
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        entityManager.merge(user);
    }
}

