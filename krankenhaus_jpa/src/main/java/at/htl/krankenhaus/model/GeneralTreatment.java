package at.htl.krankenhaus.model;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class GeneralTreatment extends Treatment {
    public String TreatmentInformation;

    public GeneralTreatment(String name, Doctor doctor, Patient patient, String outcome, LocalDateTime startDate, LocalDateTime endDate, String treatmentInformation) {
        super(name, doctor, patient, outcome, startDate, endDate);
        TreatmentInformation = treatmentInformation;
    }

    public GeneralTreatment() {
    }

    public String getTreatmentInformation() {
        return TreatmentInformation;
    }

    public void setTreatmentInformation(String treatmentInformation) {
        TreatmentInformation = treatmentInformation;
    }
}
