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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class PatientEndpointIT {
    Client client;
    WebTarget target;

    @Before
    public void Setup() {
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080/krankenhaus_jpa/api/patient");
    }

    @Test
    public void t01_simplePatientTest() {
        JsonObject patientJson = Json.createObjectBuilder()
                .add("name", "Sue Sunshine")
                .add("salary", 42578)
                .build();
        // Put
        Response putResponse = target
                .request()
                .post(Entity.json(patientJson));
        assertThat(putResponse.getStatus(), is(200));
        long id = putResponse.readEntity(long.class);

        // Get
        Response response = target
                .path(id + "")
                .request(MediaType.APPLICATION_JSON)
                .get();
        JsonObject resultJson = response.readEntity(JsonObject.class);
        assertThat(response.getStatus(), is(200));
        assertThat(resultJson.getString("name"), is("Sue Sunshine"));
        assertThat(resultJson.getJsonNumber("salary").intValue(), is(42578));

        // Delete
        Response deleteResponse = target
                .path(id + "")
                .request()
                .delete();
        assertThat(deleteResponse.getStatus(), is(204));

        Response getDeletedResponse = target
                .path(id + "")
                .request(MediaType.APPLICATION_JSON)
                .get();
        assertThat(getDeletedResponse.getStatus(), is(204));

    }
}
