package at.htl.krankenhaus.business;

import at.htl.krankenhaus.model.DrugTreatment;

import javax.ejb.Stateless;

@Stateless
public class DrugTreatmentDao extends AbstractDao<DrugTreatment> {
    public DrugTreatmentDao() {
        super(DrugTreatment.class, "DrugTreatment.findAll");
    }
}
