package at.htl.krankenhaus.business;

import at.htl.krankenhaus.model.Treatment;

import javax.ejb.Stateless;

@Stateless
public class TreatmentDao extends AbstractDao<Treatment> {
    public  TreatmentDao() {
        super(Treatment.class, "Treatment.findAll");
    }
}
