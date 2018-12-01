package at.htl.krankenhaus.rest;

import at.htl.krankenhaus.business.InitBean;
import at.htl.krankenhaus.model.DrugTreatment;
import at.htl.krankenhaus.model.GeneralTreatment;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;

@Path("generaltreatment")
public class GeneralTreatmentEndpoint {
    @Inject
    InitBean initBean;

    @PersistenceContext
    EntityManager em;

    @GET
    @Path("{id}")
    public GeneralTreatment getDoctor(@PathParam("id") long id) {
        return em.find(GeneralTreatment.class, id);
    }

    @POST
    public Long putGeneralTreatment(GeneralTreatment generalTreatment) {
        return initBean.putTreatment(generalTreatment);
    }

    @DELETE@Path("{id}")
    public void deleteGeneralTreatment(@PathParam("id") long id) {
        GeneralTreatment t = em.find(GeneralTreatment.class, id);
        if(t != null) {
            initBean.removeTreatment(t);
        }
    }
}
