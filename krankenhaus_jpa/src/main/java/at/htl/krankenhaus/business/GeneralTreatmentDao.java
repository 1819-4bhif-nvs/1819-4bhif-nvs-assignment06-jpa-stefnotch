package at.htl.krankenhaus.business;

import at.htl.krankenhaus.model.GeneralTreatment;

import javax.ejb.Stateless;

@Stateless
public class GeneralTreatmentDao extends AbstractDao<GeneralTreatment> {
    public GeneralTreatmentDao() {
        super(GeneralTreatment.class, "GeneralTreatment.findAll");
    }
}
