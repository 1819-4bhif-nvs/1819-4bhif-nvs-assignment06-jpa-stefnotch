package at.htl.krankenhaus.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public abstract class Treatment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    private Doctor doctor;
    @ManyToOne
    private Patient patient;
    private String outcome; // Doctors tend to write down quite a bunch of things

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Treatment() {
    }

    public Treatment(String name, Doctor doctor, Patient patient, String outcome, LocalDateTime startDate, LocalDateTime endDate) {
        this.name = name;
        this.doctor = doctor;
        this.patient = patient;
        this.outcome = outcome;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}
