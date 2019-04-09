package at.htl.krankenhaus.rest;

import at.htl.krankenhaus.business.InitBean;
import at.htl.krankenhaus.model.Doctor;
import at.htl.krankenhaus.model.DrugTreatment;
import at.htl.krankenhaus.model.GeneralTreatment;
import at.htl.krankenhaus.model.Patient;
import io.swagger.annotations.Api;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("generaltreatment")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
@Api(value = "General Treatment (including Homeopathy)") // *swag*
@Stateless
public class GeneralTreatmentEndpoint {
    @PersistenceContext
    EntityManager em;

    @GET
    @Path("{id}")
    public GeneralTreatment getDoctor(@PathParam("id") long id) {
        return em.find(GeneralTreatment.class, id);
    }

    @POST
    public Long putGeneralTreatment(GeneralTreatment treatment) {
        Doctor doctor = treatment.getDoctor();
        Patient patient = treatment.getPatient();

        em.merge(doctor);
        em.persist(doctor);
        em.merge(patient);
        em.persist(patient);

        em.persist(treatment);
        return treatment.getId();
    }

    @DELETE@Path("{id}")
    public void deleteGeneralTreatment(@PathParam("id") long id) {
        GeneralTreatment t = em.find(GeneralTreatment.class, id);
        if(t != null) {
            em.remove(em.contains(t) ? t : em.merge(t));
        }
    }
}
