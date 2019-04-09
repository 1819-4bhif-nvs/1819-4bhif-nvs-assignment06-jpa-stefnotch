package at.htl.krankenhaus.rest;

import at.htl.krankenhaus.business.AbstractDao;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
public abstract class AbstractEndpoint<T extends AbstractDao> {
    @Inject
    private T dao;

    public AbstractEndpoint() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return Response.ok().entity(dao.findAll()).build();
    }
}
