package com.quarksy;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/v1/developers")
@Produces(MediaType.APPLICATION_JSON)
public class DevelopersResource
{
    @Inject
    DevelopersService service;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addDev(String dev)
    {
        return service.addDev(dev);
    }

    @GET
    public Response getAllDevs()
    {
        return service.getAllDevs();
    }

    @GET
    @Path("/{developerID}")
    public Response getDevById(@PathParam("developerID") String id)
    {
        return service.getDevById(id);
    }

    @PATCH
    @Path("/{developerID}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateDevById(@PathParam("developerID") String id)
    {
        return service.updateDevById(id);
    }

    @DELETE
    @Path("/{developerID}")
    public Response removeDevById(@PathParam("developerID") String id)
    {
        return service.removeDevById(id);
    }
}