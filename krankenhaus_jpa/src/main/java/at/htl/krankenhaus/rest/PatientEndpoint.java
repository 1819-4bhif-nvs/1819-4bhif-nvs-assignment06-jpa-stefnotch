package at.htl.krankenhaus.rest;

import at.htl.krankenhaus.business.InitBean;
import at.htl.krankenhaus.model.Patient;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;

@Path("patient")
@Stateless
public class PatientEndpoint {
    @PersistenceContext
    private EntityManager em;
    @GET
    @Path("{id}")
    public Patient getPatient(@PathParam("id") long id) {
        return em.find(Patient.class, id);
    }

    @POST
    public Long putPatient(Patient patient) {
        em.persist(patient);
        return patient.getId();
    }

    @DELETE
    @Path("{id}")
    public void deletePatient(@PathParam("id") long id) {
        Patient p = em.find(Patient.class, id);
        if(p != null) {
            em.remove(em.contains(p) ? p : em.merge(p));
        }
    }
}
