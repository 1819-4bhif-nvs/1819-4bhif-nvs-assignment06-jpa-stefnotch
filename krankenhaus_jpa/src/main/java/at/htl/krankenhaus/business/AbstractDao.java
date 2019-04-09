package at.htl.krankenhaus.business;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class AbstractDao<T> {

    @PersistenceContext
    private EntityManager em;

    private final Class<T> clazz;
    private final String findAllQueryName;

    public AbstractDao(Class<T> clazz, String findAllQueryName) {
        this.clazz = clazz;
        this.findAllQueryName = findAllQueryName;
    }

    public T update(T entity) {
        return em.merge(entity);
    }

    public void insert(T entity) {
        em.persist(entity);
    }

    public T find(Long id) {
        return em.find(clazz, id);
    }

    public List<T> findAll() {
        List<T> all = em.createNamedQuery(findAllQueryName, clazz).getResultList();
        return all;
    }

    public void delete(Long id) {
        delete(find(id));
    }

    public void delete(T entity) {
        em.remove(entity);
    }
}
