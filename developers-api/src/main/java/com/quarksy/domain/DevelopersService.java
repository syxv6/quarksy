package com.quarksy.domain;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;


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


    public List<Developer> getAllDevs(String name, String team, int page, int pageSize, String sort)
    {
        List<Developer> list = Developer.listAll();
        if (name != null) {
            list = list.stream().filter(d -> d.getName().equals(name)).collect(Collectors.toList());
        }
        if (team != null) {
            list = list.stream().filter(d -> d.getTeam().equals(team)).collect(Collectors.toList());
        }
        if (sort != null) {
            String[] fields = sort.split(",");
            for (String f : fields) {
                String[] tokens = f.split(":");
                switch (tokens[0]) {
                    case "id":
                        list.sort(Comparator.comparing(Developer::getId));
                        break;
                    case "name":
                        list.sort(Comparator.comparing(Developer::getName));
                        break;
                    case "team":
                        list.sort(Comparator.comparing(Developer::getTeam));
                        break;
                    case "createdAt":
                        list.sort(Comparator.comparing(Developer::getCreatedAt));
                        break;
                    case "updatedAt":
                        list.sort(Comparator.comparing(Developer::getUpdatedAt));
                        break;
                    default:
                        throw new WebApplicationException(Response.status(BAD_REQUEST)
                                .entity("Field name " + tokens[0] + " for sorting is invalid.").build());
                }
                if (tokens[1].equals("desc"))
                    Collections.reverse(list);
                else if (!tokens[1].equals("asc"))
                    throw new WebApplicationException(Response.status(BAD_REQUEST)
                            .entity("Sorting order " + tokens[1] + " is invalid.").build());
            }
        }
        int endIndex = Math.min(page + (pageSize == 0 ? 10 : pageSize), list.size());
        return list.subList(page, endIndex);
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
