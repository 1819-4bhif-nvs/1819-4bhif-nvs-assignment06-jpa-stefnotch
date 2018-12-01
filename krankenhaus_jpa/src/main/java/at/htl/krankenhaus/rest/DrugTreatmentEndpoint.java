package at.htl.krankenhaus.rest;

import at.htl.krankenhaus.business.InitBean;
import at.htl.krankenhaus.model.Doctor;
import at.htl.krankenhaus.model.DrugTreatment;
import at.htl.krankenhaus.model.Patient;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;

@Path("drugtreatment")
public class DrugTreatmentEndpoint {

    @Inject
    InitBean initBean;

    @PersistenceContext
    EntityManager em;

    @GET
    @Path("{id}")
    public DrugTreatment getDrugTreatment(@PathParam("id") long id) {
        return em.find(DrugTreatment.class, id);
    }


    @POST
    public Long putDrugTreatment(DrugTreatment drugTreatment) {
        System.out.println("Drug Treatment");
        System.out.println(drugTreatment);
        return initBean.putTreatment(drugTreatment);
    }

    @DELETE
    @Path("{id}")
    public void deleteDrugTreatment(@PathParam("id") long id) {
        DrugTreatment t = em.find(DrugTreatment.class, id);
        if(t != null) {
            initBean.removeTreatment(t);
        }
    }
}
