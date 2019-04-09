package at.htl.krankenhaus.business;

import at.htl.krankenhaus.model.Patient;

import javax.ejb.Stateless;

@Stateless
public class PatientDao extends AbstractDao<Patient> {
    public PatientDao() {
        super(Patient.class, "Patient.findAll");
    }
}
