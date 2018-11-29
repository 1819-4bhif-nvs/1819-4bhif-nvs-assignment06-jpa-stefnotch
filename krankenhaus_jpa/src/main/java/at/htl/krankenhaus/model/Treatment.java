package at.htl.krankenhaus.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Treatment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Doctor doctor;
    private Patient patient;
    private String outcome; // Doctors tend to write down quite a bunch of things

}
