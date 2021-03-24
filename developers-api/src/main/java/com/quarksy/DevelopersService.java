package com.quarksy;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;

@ApplicationScoped
public class DevelopersService
{

    public Response getDevById(String id)
    {
        return Response.ok("by ID").build();
    }

    public Response getAllDevs()
    {
        return Response.ok("Get all developers").build();
    }

    public Response addDev(String dev)
    {
        return Response.ok("Add dev").build();
    }

    public Response updateDevById(String id)
    {
        return Response.ok("Update dev").build();
    }

    public Response removeDevById(String id)
    {
        return Response.ok("Remove dev").build();
    }
}
