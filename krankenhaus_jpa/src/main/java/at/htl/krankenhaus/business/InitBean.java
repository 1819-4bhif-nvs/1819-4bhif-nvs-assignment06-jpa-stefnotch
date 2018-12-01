package at.htl.krankenhaus.business;

import at.htl.krankenhaus.model.Doctor;
import at.htl.krankenhaus.model.DrugTreatment;
import at.htl.krankenhaus.model.Patient;
import at.htl.krankenhaus.model.Treatment;

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

    public Long putPatient(Patient patient) {
        em.persist(patient);
        return patient.getId();
    }

    public void removePatient(Patient p) {
        em.remove(em.contains(p) ? p : em.merge(p));
    }

    public Long putTreatment(Treatment treatment) {
        em.persist(treatment);
        return treatment.getId();
    }

    public void removeTreatment(Treatment t) {em.remove(em.contains(t) ? t : em.merge(t));
    }
}
