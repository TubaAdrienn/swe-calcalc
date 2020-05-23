package caloriescalc.dao;

import caloriescalc.model.UserData;
import caloriescalc.util.JPAdao;

import javax.persistence.Persistence;

public class JournalDao extends JPAdao<UserData> {

    private static JournalDao instance;

    private JournalDao() {
        super(UserData.class);
    }

    public static JournalDao getInstance() {
        if (instance == null) {
            instance = new JournalDao();
            instance.setEntityManager(Persistence.createEntityManagerFactory("test").createEntityManager());
        }
        return instance;
    }
}