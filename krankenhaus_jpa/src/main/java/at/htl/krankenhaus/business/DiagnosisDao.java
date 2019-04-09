package at.htl.krankenhaus.business;

import at.htl.krankenhaus.model.Diagnosis;

import javax.ejb.Stateless;

@Stateless
public class DiagnosisDao extends AbstractDao<Diagnosis> {
    public DiagnosisDao() {
        super(Diagnosis.class, "Diagnosis.findAll");
    }
}
