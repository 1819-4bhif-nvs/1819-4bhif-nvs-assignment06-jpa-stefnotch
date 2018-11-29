package at.htl.krankenhaus.model;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class DrugTreatment extends Treatment {
    public String DrugName;
    public double DosePerDay;

    public DrugTreatment(String name, Doctor doctor, Patient patient, String outcome, LocalDateTime startDate, LocalDateTime endDate, String drugName, double dosePerDay) {
        super(name, doctor, patient, outcome, startDate, endDate);
        DrugName = drugName;
        DosePerDay = dosePerDay;
    }

    public DrugTreatment() {
    }

    public String getDrugName() {
        return DrugName;
    }

    public void setDrugName(String drugName) {
        DrugName = drugName;
    }

    public double getDosePerDay() {
        return DosePerDay;
    }

    public void setDosePerDay(double dosePerDay) {
        DosePerDay = dosePerDay;
    }
}
