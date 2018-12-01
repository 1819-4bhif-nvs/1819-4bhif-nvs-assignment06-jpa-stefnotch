package at.htl.krankenhaus.model;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class DrugTreatment extends Treatment {
    private String drugName;
    private double dosePerDay;

    public DrugTreatment(String name, Doctor doctor, Patient patient, String outcome, LocalDate startDate, LocalDate endDate, String drugName, double dosePerDay) {
        super(name, doctor, patient, outcome, startDate, endDate);
        this.drugName = drugName;
        this.dosePerDay = dosePerDay;
    }

    public DrugTreatment() {
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public double getDosePerDay() {
        return dosePerDay;
    }

    public void setDosePerDay(double dosePerDay) {
        this.dosePerDay = dosePerDay;
    }
}
