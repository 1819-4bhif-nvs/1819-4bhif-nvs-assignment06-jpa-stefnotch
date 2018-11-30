package at.htl.krankenhaus.rest;

import at.htl.krankenhaus.model.Doctor;
import at.htl.krankenhaus.model.DrugTreatment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;

@Path("drugtreatment")
public class DrugTreatmentEndpoint {

    @PersistenceContext
    EntityManager em;

    @GET
    @Path("{id}")
    public DrugTreatment getDoctor(@PathParam("id") long id) {
        return em.find(DrugTreatment.class, id);
    }

    @POST
    public void putDrugTreatment(DrugTreatment drugTreatment) {
        em.getTransaction().begin();
        em.persist(drugTreatment);
        em.getTransaction().commit();
    }

    @DELETE
    public void deleteDrugTreatment(DrugTreatment drugTreatment) {
        em.getTransaction().begin();
        em.remove(drugTreatment);
        em.getTransaction().commit();
    }
}
