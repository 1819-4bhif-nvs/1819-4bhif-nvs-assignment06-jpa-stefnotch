package at.htl.krankenhaus.rest;

import at.htl.krankenhaus.business.AbstractDao;
import at.htl.krankenhaus.model.Doctor;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
public abstract class AbstractEndpoint<T, U extends AbstractDao<T>> {
    @Inject
    private U dao;

    public AbstractEndpoint() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return Response.ok().entity(dao.findAll()).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getSingle(@PathParam("id") long id) {
        T single = dao.find(id);
        if(single != null) {
            return Response.ok().entity(single).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    // TODO: Post

    // TODO: Put

    // Delete
    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") long id) {
        dao.delete(id);

        return Response.noContent().build();
    }
}
