package com.quarksy.domain;

import io.quarkus.panache.common.Sort;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
public class DevelopersService
{

    @Inject
    EntityManager em;

    public Developer getDevById(String id)
    {
        Developer dev = Developer.findById(id);
        if (dev == null) {
            throw new WebApplicationException("Developer with id of " + id + " does not exist.", 404);
        }
        return dev;
    }


    public List<Developer> getAllDevs()
    {
        return Developer.listAll(Sort.by("id"));
    }

    public Response addDev(Developer dev)
    {
        if (dev.id == null) {
            throw new WebApplicationException("Id was invalidly set on request.", 422);
        }
        dev.persist();
        return Response.ok(dev).status(201).build();
    }

    public Developer updateDevById(String id, Developer devNew)
    {
        if (devNew.name == null) {
            throw new WebApplicationException("Developer Name was not set on request.", 422);
        }

        Developer dev = Developer.findById(id);
        if (dev == null) {
            throw new WebApplicationException("Developer with id of " + id + " does not exist.", 404);
        }

        dev.name = devNew.name;
        dev.team = devNew.team;

        return dev;
    }

    public Response removeDevById(String id)
    {
        Developer dev = Developer.findById(id);
        if (dev == null) {
            throw new WebApplicationException("Developer with id of " + id + " does not exist.", 404);
        }
        dev.delete();
        return Response.status(204).build();
    }
}
