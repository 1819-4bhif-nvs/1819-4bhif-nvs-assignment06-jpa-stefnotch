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
}
