package at.htl.krankenhaus.rest;

import at.htl.krankenhaus.business.InitBean;
import at.htl.krankenhaus.model.Doctor;
import at.htl.krankenhaus.model.DrugTreatment;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import java.util.List;

@Path("doctor")
public class DoctorEndpoint {

    @Inject InitBean initBean;

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
        return initBean.putDoctor(doctor);
    }

    @DELETE
    @Path("{id}")
    public void deleteDoctor(@PathParam("id") long id) {
        Doctor d = em.find(Doctor.class, id);
        if(d != null) {
            initBean.removeDoctor(d);
        }
    }
}
