package at.htl.krankenhaus.business;

import at.htl.krankenhaus.model.Doctor;

import javax.ejb.Stateless;

@Stateless
public class DoctorDao extends AbstractDao<Doctor> {
    public DoctorDao() {
        super(Doctor.class, "Doctor.findAll");
    }
}
