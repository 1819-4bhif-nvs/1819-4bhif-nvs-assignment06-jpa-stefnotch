package at.htl.krankenhaus.rest;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class DoctorEndpointIT {
    Client client;
    WebTarget target;

    @Before
    public void Setup() {
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080/krankenhaus_jpa/api/doctor");
    }

    @Test
    public void t01_getDeletedDoctor() {
        target
                .path("1")
                .request()
                .delete();

        Response response = target
                .path("1")
                .request(MediaType.APPLICATION_JSON)
                .get();

        assertThat(response.getStatus(), is(204)); // No Content
    }

    @Test
    public void t02_getExistingDoctorById() {
        JsonObject doctorJson = Json.createObjectBuilder()
                .add("name", "Sally Sunshine")
                .add("salary", 45)
                .build();
        Response putResponse = target
                .request()
                .post(Entity.json(doctorJson));
        long id = putResponse.readEntity(long.class);

        Response response = target
                .path(id + "")
                .request(MediaType.APPLICATION_JSON)
                .get();
        assertThat(response.getStatus(), is(200));
    }

    @Test
    public void t03_getExistingDoctorByName() {
        // Add
        JsonObject doctorJson = Json.createObjectBuilder()
                .add("name", "John Smith")
                .add("salary", 46.45)
                .build();
        target
                .request()
                .post(Entity.json(doctorJson));

        // Read
        Response response = target
                .path("name")
                .path("John Smith")
                .request(MediaType.APPLICATION_JSON)
                .get();
        assertThat(response.getStatus(), is(200));

        JsonObject resultJson = response.readEntity(JsonObject.class);

        // Clean-up
        target
                .path(resultJson.getJsonNumber("id").intValue() + "")
                .request()
                .delete();
    }

    @Test
    public void t02_getDoctors() {

    }

    @Test
    public void t10_putDoctor() {

    }

    @Test
    public void t20_deleteDoctor() {

    }
}
