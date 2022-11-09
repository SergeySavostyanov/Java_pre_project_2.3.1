package web.test;

import web.dao.UserDaoHibernateImpl;
import web.service.UserService;
import web.service.UserServiceImpl;

public class ma {
    public static void main(String[] args) {
        UserService impl = new UserServiceImpl();
        impl.createUsersTable();
        impl.saveUser("Джо", "Байден", (byte) 78);
        impl.saveUser("Трамп", "Дональд", (byte) 74);
        impl.saveUser("Барак", "Обама", (byte) 59);
        impl.saveUser("Джордж", "Буш", (byte) 74);

        impl.removeUserById(3);

        impl.getAllUsers().stream().forEach(user -> System.out.println(user.toString()));
        impl.cleanUsersTable();
        impl.dropUsersTable();
    }
}
