package web.util;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.persistence.EntityManagerFactory;

import java.util.Properties;
public class Util {
    private final static String URL = "jdbc:mysql://localhost:3306/mydbstest";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "123456as";
    private static EntityManagerFactory entityManagerFactory;
    static {
        try {
            Properties prop = new Properties();
            prop.setProperty("hibernate.connection.url", URL);
            prop.setProperty("hibernate.connection.username", USERNAME);
            prop.setProperty("hibernate.connection.password", PASSWORD);
            prop.setProperty("hibernate.show_sql","true");
            prop.setProperty("hibernate.default_schema","mydbstest");
            prop.setProperty("hibernate.hibernate.format_sql","true");
            prop.setProperty("hibernate.hibernate.current_session_context_class", "thread");
            prop.setProperty("hibernate.hbm2ddl.auto", "create");

            entityManagerFactory = new org.hibernate.cfg.Configuration()
                    .addProperties(prop)
                    //.addPackage("com.kat")
                    .addAnnotatedClass(web.model.User.class)
                    .buildSessionFactory()
            ;
        }
        catch (Exception ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
}
