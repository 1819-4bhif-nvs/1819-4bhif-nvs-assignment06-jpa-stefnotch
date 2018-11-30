package at.htl.krankenhaus.rest;

import at.htl.krankenhaus.model.Patient;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;

@Path("patient")
public class PatientEndpoint {
    @PersistenceContext
    private EntityManager em;
    @GET
    @Path("{id}")
    public Patient getPatient(@PathParam("id") long id) {
        return em.find(Patient.class, id);
    }

    @POST
    public void putPatient(Patient patient) {
        em.getTransaction().begin();
        em.persist(patient);
        em.getTransaction().commit();
    }

    @DELETE
    public void deletePatient(Patient patient) {
        em.getTransaction().begin();
        em.remove(patient);
        em.getTransaction().commit();
    }
}
