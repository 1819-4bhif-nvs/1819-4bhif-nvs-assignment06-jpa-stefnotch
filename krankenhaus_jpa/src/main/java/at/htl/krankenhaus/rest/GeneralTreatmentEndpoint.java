package at.htl.krankenhaus.rest;

import at.htl.krankenhaus.model.DrugTreatment;
import at.htl.krankenhaus.model.GeneralTreatment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;

@Path("generaltreatment")
public class GeneralTreatmentEndpoint {

    @PersistenceContext
    EntityManager em;

    @GET
    @Path("{id}")
    public GeneralTreatment getDoctor(@PathParam("id") long id) {
        return em.find(GeneralTreatment.class, id);
    }

    @POST
    public void putGeneralTreatment(GeneralTreatment generalTreatment) {
        em.getTransaction().begin();
        em.persist(generalTreatment);
        em.getTransaction().commit();
    }

    @DELETE
    public void deleteGeneralTreatment(GeneralTreatment generalTreatment) {
        em.getTransaction().begin();
        em.remove(generalTreatment);
        em.getTransaction().commit();
    }
}
