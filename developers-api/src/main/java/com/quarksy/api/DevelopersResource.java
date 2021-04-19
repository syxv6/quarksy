package com.quarksy.api;

import com.quarksy.domain.Developer;
import com.quarksy.domain.DevelopersService;
import org.jboss.logging.Logger;

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
    @Inject
    DevelopersService service;

    private static final Logger logger = Logger.getLogger(DevelopersResource.class.getName());

    @POST
    @Transactional
    public Response create(Developer developer)
    {
        logger.info("POST Developer");
        return service.addDev(developer);
    }

    @GET
    public List<Developer> getAllDevs()
    {
        logger.info("Get All Developers");
        return service.getAllDevs();
    }
 
    @GET
    @Path("{id}")
    public Developer getDevById(@PathParam("id") String id)
    {
        logger.info("Get Developer By ID");
        return service.getDevById(id);
    }

    @PATCH
    @Path("/{id}")
    public Developer updateDevById(@PathParam("id") String id, Developer dev)
    {
        logger.info("Update Developer By ID");
        return service.updateDevById(id, dev);
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") String id)
    {
        logger.info("Delete Developer By ID");
        return service.removeDevById(id);
    }

}