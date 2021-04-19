package com.quarksy.api;

import com.quarksy.domain.Developer;
import com.quarksy.domain.DevelopersService;
import com.quarksy.domain.Skill;
import io.quarkus.panache.common.Sort;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/api/v1/developers")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DevelopersResource
{

    @POST
    @Transactional
    public Response create(Developer developer) {
        if (developer.id == null) {
            throw new WebApplicationException("Id was invalidly set on request.", 422);
        }
        developer.persist();
        return Response.ok(developer).status(201).build();
    }

    @GET
    public List<Developer> getAllDevs()
    {
        return Developer.listAll(Sort.by("id"));
    }

    @GET
    @Path("{id}")
    public Developer getSingle(@org.jboss.resteasy.annotations.jaxrs.PathParam String id) {
        Developer entity = Developer.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Developer with id of " + id + " does not exist.", 404);
        }
        return entity;
    }

//    @PATCH
//    @Path("/{developerID}")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response updateDevById(@PathParam("developerID") String id)
//    {
//        return service.updateDevById(id);
//    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@org.jboss.resteasy.annotations.jaxrs.PathParam String id) {
        Developer entity = Developer.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Developer with id of " + id + " does not exist.", 404);
        }
        entity.delete();
        return Response.status(204).build();
    }


}