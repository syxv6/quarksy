package com.quarksy.api;

import com.quarksy.domain.Developer;
import com.quarksy.domain.Developers;
import com.quarksy.domain.DevelopersService;
import com.quarksy.error.ErrorEntity;
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
        try {
            return service.addDev(developer);
        } catch (WebApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw new WebApplicationException(Response.status(500).entity(new ErrorEntity()).build());
        }
    }

    @GET
    public Developers getAllDevs(@QueryParam("name") String name,
                                 @QueryParam("team") String team,
                                 @QueryParam("page") int page,
                                 @QueryParam("pageSize") int pageSize,
                                 @QueryParam("sort") String sort)
    {
        logger.info("Get All Developers");

        try {
            Developers developers = new Developers();
            var getAllDevsMap = service.getAllDevs(name, team, page, pageSize, sort);
            developers.setHasNext((Boolean) getAllDevsMap.get("hasNext"));
            developers.setItems((List<Developer>) getAllDevsMap.get("devList"));

            return developers;
        } catch (WebApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw new WebApplicationException(Response.status(500).entity(new ErrorEntity()).build());
        }
    }

    @GET
    @Path("{id}")
    public Developer getDevById(@PathParam("id") String id)
    {
        logger.info("Get Developer By ID");

        try {
            return service.getDevById(id);
        } catch (WebApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw new WebApplicationException(Response.status(500).entity(new ErrorEntity()).build());
        }
    }

    @PATCH
    @Path("/{id}")
    public Developer updateDevById(@PathParam("id") String id, Developer dev)
    {
        logger.info("Update Developer By ID");

        try {
            return service.updateDevById(id, dev);
        } catch (WebApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw new WebApplicationException(Response.status(500).entity(new ErrorEntity()).build());
        }
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") String id)
    {
        logger.info("Delete Developer By ID");

        try {
            return service.removeDevById(id);
        } catch (WebApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw new WebApplicationException(Response.status(500).entity(new ErrorEntity()).build());
        }
    }

}