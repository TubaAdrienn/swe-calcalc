package caloriescalc.dao;

import caloriescalc.model.UserData;
import caloriescalc.util.JPADao;

import javax.persistence.Persistence;

/**
 * DAO class for the {@link UserData} entities.
 */
public class UserDao extends JPADao<UserData> {

    private static UserDao instance;

    private UserDao() {
        super(UserData.class);
    }

    /**
     * Constucts a singleton instance of a DAO object.
     *
     * @return the object
     */
    public static UserDao getInstance() {
        if (instance == null) {
            instance = new UserDao();
            instance.setEntityManager(Persistence.createEntityManagerFactory("test").createEntityManager());
        }
        return instance;
    }
}