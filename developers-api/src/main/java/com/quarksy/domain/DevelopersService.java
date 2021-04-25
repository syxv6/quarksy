package com.quarksy.domain;

import io.quarkus.panache.common.Sort;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.Response.Status.*;

@ApplicationScoped
public class DevelopersService
{

    @Inject
    EntityManager em;

    public Developer getDevById(String id)
    {
        Developer dev = Developer.findById(id);
        if (dev == null) {
            throw new WebApplicationException(
                    Response.status(NOT_FOUND).entity("Developer with id of " + id + " does not exist.").build());
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
            throw new WebApplicationException(
                    Response.status(BAD_REQUEST).entity("Id was invalidly set on request.").build());
        }
        dev.persist();
        return Response.ok(dev).status(CREATED).build();
    }

    public Developer updateDevById(String id, Developer devNew)
    {
        if (devNew.name == null) {
            throw new WebApplicationException(
                    Response.status(BAD_REQUEST).entity("Developer Name was not set on request.").build());
        }

        Developer dev = Developer.findById(id);
        if (dev == null) {
            throw new WebApplicationException(
                    Response.status(NOT_FOUND).entity("Developer with id of " + id + " does not exist.").build());
        }

        dev.name = devNew.name;
        dev.team = devNew.team;

        return dev;
    }

    public Response removeDevById(String id)
    {
        Developer dev = Developer.findById(id);
        if (dev == null) {
            throw new WebApplicationException(
                    Response.status(NOT_FOUND).entity("Developer with id of " + id + " does not exist.").build());
        }
        dev.delete();
        return Response.status(204).build();
    }
}
