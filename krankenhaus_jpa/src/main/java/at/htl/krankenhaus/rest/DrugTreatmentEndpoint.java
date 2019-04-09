package at.htl.krankenhaus.rest;

import at.htl.krankenhaus.business.InitBean;
import at.htl.krankenhaus.model.Doctor;
import at.htl.krankenhaus.model.DrugTreatment;
import at.htl.krankenhaus.model.Patient;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("drugtreatment")
@Stateless
public class DrugTreatmentEndpoint {
    @PersistenceContext
    EntityManager em;


    // Improve it liek dis: https://github.com/1819-4bhif-nvs/1819-4bhif-nvs-assignment06-jpa-ErikMayrhofer/blob/master/Kursverwaltung/src/main/java/at/htl/kursverwaltung/rest/AbstractEndpoint.java
    @GET
    @Path("{id}")
    //@Produces(MediaType.APPLICATION_JSON)
    public DrugTreatment getDrugTreatment(@PathParam("id") long id) {
        DrugTreatment drugTreatment = em.find(DrugTreatment.class, id);
/*
        return Response
                .ok()
                .entity(drugTreatment)
                .build();*/
        return drugTreatment;
    }


    @POST
    public Long putDrugTreatment(DrugTreatment treatment) {
        Doctor doctor = treatment.getDoctor();
        Patient patient = treatment.getPatient();

        em.merge(doctor);
        em.persist(doctor);
        em.merge(patient);
        em.persist(patient);

        em.persist(treatment);
        return treatment.getId();
    }

    @DELETE
    @Path("{id}")
    public void deleteDrugTreatment(@PathParam("id") long id) {
        DrugTreatment t = em.find(DrugTreatment.class, id);
        if(t != null) {
            em.remove(em.contains(t) ? t : em.merge(t));
        }
    }
}
