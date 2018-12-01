package at.htl.krankenhaus.rest;

import org.junit.Before;
import org.junit.Test;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class KrankenhausEndpointsST {
    Client client;
    WebTarget target;

    @Before
    public void Setup() {
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080/krankenhaus_jpa/api");
    }

    @Test
    public void t01_entireHospital() {
        // JSON Objects
        JsonObject patientJson = Json.createObjectBuilder()
                .add("name", "Patient")
                .add("salary", 666)
                .build();

        JsonObject doctorJson = Json.createObjectBuilder()
                .add("name", "Doctor")
                .add("salary", 666)
                .build();

        JsonObject drugTreatmentJson = Json.createObjectBuilder()
                .add("name", "Anti-Alcoholism Treatment")
                .add("drugName", "Antabuse")
                .add("dosePerDay", "30 mg, twice daily")
                //.add("doctor", doctorJson)
                //.add("patient", patientJson)
                .add("outcome", "Success, however patient gained 20 pounds.")
                //.add("startDate", DateTimeFormatter.ISO_DATE.format(LocalDate.of(2018, 12, 4)))
                //.add("endDate", DateTimeFormatter.ISO_DATE.format(LocalDate.of(2019, 1, 1)))
                .build();

        JsonObject generalTreatmentJson = Json.createObjectBuilder()
                .add("name", "Psychological Counseling")
                .add("treatmentInformation", "Post Anti-Alcoholism Treatment Treatment")
                .add("doctor", doctorJson)
                .add("patient", patientJson)
                .add("outcome", JsonObject.NULL)
                .add("startDate", DateTimeFormatter.ISO_DATE.format(LocalDate.of(2019, 1, 1)))
                .add("endDate", DateTimeFormatter.ISO_DATE.format(LocalDate.of(2019, 5, 1)))
                .build();

        // Put
        Response patientPutResponse = target
                .path("patient")
                .request(MediaType.TEXT_PLAIN)
                .post(Entity.json(patientJson));
        assertThat(patientPutResponse.getStatus(), is(200));
        long patientId = patientPutResponse.readEntity(long.class);

        Response doctorPutResponse = target
                .path("doctor")
                .request(MediaType.TEXT_PLAIN)
                .post(Entity.json(doctorJson));
        assertThat(doctorPutResponse.getStatus(), is(200));
        long doctorId = doctorPutResponse.readEntity(long.class);

        Response drugTreatmentPutResponse = target
                .path("drugtreatment")
                .request(MediaType.TEXT_PLAIN)
                .post(Entity.json(drugTreatmentJson));
        System.out.println(drugTreatmentPutResponse.getStatus());
        assertThat(drugTreatmentPutResponse.getStatus(), is(200));
        long drugTreatmentId = drugTreatmentPutResponse.readEntity(long.class);

        Response generalTreatmentResponse = target
                .path("generaltreatment")
                .request(MediaType.TEXT_PLAIN)
                .post(Entity.json(generalTreatmentJson));
        assertThat(generalTreatmentResponse.getStatus(), is(200));
        long generalTreatmentId = generalTreatmentResponse.readEntity(long.class);

        // Get
        // Patient
        Response patientGetResponse = target
                .path(patientId + "")
                .request(MediaType.APPLICATION_JSON)
                .get();
        JsonObject patientResultJson = patientGetResponse.readEntity(JsonObject.class);

        assertThat(patientGetResponse.getStatus(), is(200));
        assertThat(patientResultJson.getString("name"), is("Patient"));
        assertThat(patientResultJson.getJsonNumber("salary").intValue(), is(666));

        // Doctor
        Response doctorGetResponse = target
                .path(doctorId + "")
                .request(MediaType.APPLICATION_JSON)
                .get();
        JsonObject doctorResultJson = doctorGetResponse.readEntity(JsonObject.class);

        assertThat(doctorGetResponse.getStatus(), is(200));
        assertThat(doctorResultJson.getString("name"), is("Doctor"));
        assertThat(doctorResultJson.getJsonNumber("salary").intValue(), is(666));

        // Drug Treatment
        Response drugTreatmentGetResponse = target
                .path(drugTreatmentId + "")
                .request(MediaType.APPLICATION_JSON)
                .get();
        JsonObject drugTreatmentResultJson = drugTreatmentGetResponse.readEntity(JsonObject.class);

        assertThat(drugTreatmentGetResponse.getStatus(), is(200));
        assertThat(drugTreatmentResultJson.getString("name"), is("Anti-Alcoholism Treatment"));
        assertThat(drugTreatmentResultJson.getString("drugName"), is("Antabuse"));
        assertThat(drugTreatmentResultJson.getJsonObject("patient").getJsonString("name"), is("Patient"));
        assertThat(LocalDate.parse(drugTreatmentResultJson.getJsonString("endDate").getString(), DateTimeFormatter.ISO_DATE),
                is(LocalDate.of(2019, 1, 1)));

        // General Treatment
        Response generalTreatmentGetResponse = target
                .path(generalTreatmentId + "")
                .request(MediaType.APPLICATION_JSON)
                .get();
        JsonObject generalTreatmentResultJson = generalTreatmentGetResponse.readEntity(JsonObject.class);

        assertThat(generalTreatmentGetResponse.getStatus(), is(200));
        assertThat(generalTreatmentResultJson.getString("name"), is("Psychological Counseling"));
        assertThat(generalTreatmentResultJson.getString("treatmentInformation"), is("Post Anti-Alcoholism Treatment Treatment"));
        assertThat(generalTreatmentResultJson.getJsonObject("patient").getJsonString("name"), is("Patient"));
        assertThat(LocalDate.parse(generalTreatmentResultJson.getJsonString("endDate").getString(), DateTimeFormatter.ISO_DATE),
                is(LocalDate.of(2019, 5, 1)));

        // Cleanup
        target
                .path(patientId + "")
                .request()
                .delete();
        target
                .path(doctorId + "")
                .request()
                .delete();
        target
                .path(drugTreatmentId + "")
                .request()
                .delete();
        target
                .path(generalTreatmentId + "")
                .request()
                .delete();
    }
}
