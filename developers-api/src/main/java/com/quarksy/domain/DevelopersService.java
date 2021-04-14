package com.quarksy.domain;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.core.Response;

@ApplicationScoped
public class DevelopersService
{

    @Inject
    EntityManager em;

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
        em.persist(dev);
        return Response.ok("Dev added").build();
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
