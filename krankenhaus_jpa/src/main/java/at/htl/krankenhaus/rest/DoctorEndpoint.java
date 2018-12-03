package at.htl.krankenhaus.rest;

import at.htl.krankenhaus.business.InitBean;
import at.htl.krankenhaus.model.Doctor;
import at.htl.krankenhaus.model.DrugTreatment;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import java.util.List;

@Path("doctor")
@Stateless
public class DoctorEndpoint {
    @PersistenceContext
    EntityManager em;

    @GET
    public List<Doctor> getAllDoctors() {
        return em.createNamedQuery("Doctor.findAll", Doctor.class).getResultList();
    }

    @GET
    @Path("{id}")
    public Doctor getDoctor(@PathParam("id") long id) {
        return em.find(Doctor.class, id);
    }

    @GET
    @Path("/name/{name}")
    public Doctor getDoctor(@PathParam("name") String name) {
        return em.createNamedQuery("Doctor.findByName", Doctor.class).setParameter("name", name).getSingleResult();
    }

    @POST
    public Long putDoctor(Doctor doctor) {
        em.persist(doctor);
        return doctor.getId();
    }

    @DELETE
    @Path("{id}")
    public void deleteDoctor(@PathParam("id") long id) {
        Doctor d = em.find(Doctor.class, id);
        if(d != null) {
            em.remove(em.contains(d) ? d : em.merge(d));
        }
    }
}
