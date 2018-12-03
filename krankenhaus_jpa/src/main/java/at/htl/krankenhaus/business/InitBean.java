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
import java.time.LocalDate;

@Startup
@Singleton
public class InitBean {
    @PersistenceContext
    EntityManager em;

    public InitBean() {

    }

    @PostConstruct
    private void init() {
        /*
        Doctor doc =
                new Doctor("testDoctor", 0);
        Patient patient =
                new Patient("testSubject", 0);
        DrugTreatment dt = new DrugTreatment(
                "test",
                doc,
                patient,
                "no outcome",
                LocalDate.of(1999, 9, 9),
                LocalDate.of(1999, 9, 19),
                "testDrug",
                3
                );
        em.persist(doc);
        em.persist(patient);
        em.persist(dt);
        */
    }
/*
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
        Doctor doctor = treatment.getDoctor();
        Patient patient = treatment.getPatient();

        em.merge(doctor);
        em.persist(doctor);
        em.merge(patient);
        em.persist(patient);

        em.persist(treatment);
        return treatment.getId();
    }

    public void removeTreatment(Treatment t) {em.remove(em.contains(t) ? t : em.merge(t));
    }*/
}
