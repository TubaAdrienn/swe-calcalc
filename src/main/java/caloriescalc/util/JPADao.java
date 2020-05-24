package caloriescalc.util;

import org.tinylog.Logger;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Generic JPA DAO class that provides JPA support for the entity class
 * specified.
 *
 * @param <T> the type of the entity class
 */
public abstract class JPADao<T> {

    protected Class<T> entityClass;

    protected EntityManager entityManager;

    /**
     * Constructs a {@code GenericJpaDao} object.
     *
     * @param entityClass the {@link Class} object that represents the entity
     *                    class
     */
    public JPADao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * Returns the underlying {@link EntityManager} instance.
     *
     * @return the underlying {@link EntityManager} instance
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * Sets the underlying {@link EntityManager} instance.
     *
     * @param entityManager the underlying {@link EntityManager} instance
     */
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Persists the specified entity instance in the database.
     *
     * @param entity the entity instance to be persisted in the database
     */
    public void persist(T entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    /**
     * Returns the list of all instances of the entity class from the database.
     *
     * @return the list of all instances of the entity class from the database
     */
    public List<T> findAll() {
        TypedQuery<T> typedQuery = entityManager.createQuery("FROM " + entityClass.getSimpleName(), entityClass);
        Logger.debug("List: {}"+typedQuery.getResultList());
        return typedQuery.getResultList();
    }


}