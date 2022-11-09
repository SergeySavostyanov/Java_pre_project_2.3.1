package web.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;
import web.util.Util;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.PreparedStatement;
import java.util.List;
@Repository
public class UserDaoHibernateImpl implements UserDao {
    @PersistenceContext
    EntityManager entityManager;
    @Transactional
    public void createUsersTable() {


            entityManager.createNativeQuery("CREATE TABLE IF NOT EXISTS `mydbstest`.`users` (\n" +
                    "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` VARCHAR(45) NOT NULL,\n" +
                    "  `last_name` VARCHAR(45) NOT NULL,\n" +
                    "  `age` INT NULL,\n" +
                    "  PRIMARY KEY (`id`));");


    }

    public void dropUsersTable() {
        EntityTransaction transaction = null;
        EntityManager entityManager = Util.getEntityManagerFactory().createEntityManager();
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Query query = entityManager.createNativeQuery("DROP TABLE IF EXISTS users");
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {

            transaction.rollback();
            System.out.println("Ошибка при удалении таблицы users  из БД");
        } finally {
            entityManager.close();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        EntityTransaction transaction = null;
        EntityManager entityManager = Util.getEntityManagerFactory().createEntityManager();
        try {

            transaction = entityManager.getTransaction();
            transaction.begin();
            User user = new User(name, lastName, (byte) age);
            entityManager.persist(user);

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Ошибка при добавлении пользователя в таблицу");
        } finally {
            entityManager.close();
        }


    }

    public void removeUserById(long id) {
        EntityTransaction transaction = null;
        EntityManager entityManager = Util.getEntityManagerFactory().createEntityManager();
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.remove(entityManager.find(User.class,id));

            transaction.commit();

        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Не удалось удалить пользователь с id=" + id);
        }finally {
            entityManager.close();
        }

    }

    public List<User> getAllUsers() {
        EntityManager entityManager = Util.getEntityManagerFactory().createEntityManager();
        List resultList = null;
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.select(root);
            Query query = entityManager.createQuery(criteriaQuery);
            resultList = query.getResultList();
        } catch (Exception e) {
            System.out.println("Ошибка при создании списка");
        }finally {
            entityManager.close();
        }

        return resultList;
    }

    public void cleanUsersTable() {
        EntityTransaction transaction = null;
        EntityManager entityManager = Util.getEntityManagerFactory().createEntityManager();
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Query query = entityManager.createNativeQuery("TRUNCATE TABLE users");
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {

            transaction.rollback();
            System.out.println("Не удалось очистить таблицу");
        } finally {
            entityManager.close();
        }

    }
}
