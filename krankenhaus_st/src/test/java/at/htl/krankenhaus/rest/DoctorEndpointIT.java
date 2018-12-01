package at.htl.krankenhaus.rest;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
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
    public void t02_pugAndGetExistingDoctorById() {
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
        JsonObject resultJson = response.readEntity(JsonObject.class);

        assertThat(response.getStatus(), is(200));
        assertThat(resultJson.getString("name"), is("Sally Sunshine"));
        assertThat(resultJson.getJsonNumber("salary").intValue(), is(45));
    }

    @Test
    public void t03_putAndGetExistingDoctorByName() {
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
        JsonObject resultJson = response.readEntity(JsonObject.class);

        assertThat(response.getStatus(), is(200));
        assertThat(resultJson.getString("name"), is("John Smith"));
        assertThat(resultJson.getJsonNumber("salary").doubleValue(), closeTo(46.45, 0.1));


        // Clean-up
        target
                .path(resultJson.getJsonNumber("id").intValue() + "")
                .request()
                .delete();
    }

    @Test
    public void t04_getAllDoctors() {
        JsonObject doctorJson = Json.createObjectBuilder()
                .add("name", "Hello there")
                .add("salary", 575478.46)
                .build();
        target
                .request()
                .post(Entity.json(doctorJson));

        Response response = target
                .request(MediaType.APPLICATION_JSON)
                .get();

        JsonArray resultJson = response.readEntity(JsonArray.class);

        assertThat(resultJson.isEmpty(), is(false));
    }
}
