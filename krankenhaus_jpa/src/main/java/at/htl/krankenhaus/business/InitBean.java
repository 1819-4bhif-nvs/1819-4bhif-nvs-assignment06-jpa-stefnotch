package at.htl.krankenhaus.business;

import at.htl.krankenhaus.model.Doctor;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Startup
@Singleton
public class InitBean {
    @PersistenceContext
    EntityManager em;

    public InitBean() {

    }

    @PostConstruct
    private void init() {
    }

    public Long putDoctor(Doctor doctor) {
        em.persist(doctor);

        return doctor.getId();
    }

    public void removeDoctor(Doctor d) {
        em.remove(em.contains(d) ? d : em.merge(d));
    }
}
