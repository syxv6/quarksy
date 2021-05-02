package com.quarksy.domain;

import com.quarksy.error.ErrorEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.*;
import java.util.stream.Collectors;


@ApplicationScoped
public class DevelopersService
{

    @Inject
    EntityManager em;

    public Developer getDevById(String id)
    {
        Developer dev = Developer.findById(id);
        if (dev == null) {
            throw new WebApplicationException(Response.status(404).entity(
                    new ErrorEntity(404, "Developer with id of " + id + " does not exist.")).build());
        }
        return dev;
    }


    public HashMap<Object, Object> getAllDevs(String name, String team, int page, int pageSize, String sort)
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
                        throw new WebApplicationException(Response.status(400).entity(
                                new ErrorEntity(400, "Field name " + tokens[0] + " for sorting is invalid.")).build());
                }
                if (tokens[1].equals("desc"))
                    Collections.reverse(list);
                else if (!tokens[1].equals("asc"))
                    throw new WebApplicationException(Response.status(400).entity(
                            new ErrorEntity(400, "Sorting order " + tokens[1] + " is invalid.")).build());
            }
        }
        int endIndex = Math.min(page + (pageSize == 0 ? 10 : pageSize), list.size());

        HashMap<Object, Object> getAllDevsMap = new HashMap<>();

        getAllDevsMap.put("devList", list.subList(page, endIndex));
        getAllDevsMap.put("hasNext", endIndex < list.size());

        return getAllDevsMap;
    }

    public Response addDev(Developer dev)
    {
        if (dev.id == null) {
            throw new WebApplicationException(Response.status(422).entity(
                    new ErrorEntity(422, "Id was invalidly set on request.")).build());
        }
        System.out.println(dev.toString());
        ArrayList<Skill> skillList = new ArrayList<>();

        for (String skillName : dev.getSkills()) {
            Skill skill = new Skill(skillName);
            skill.setDeveloper(dev);
            skillList.add(skill);
        }
        dev.skills = skillList;
        dev.persist();
        return Response.ok(dev).status(201).build();
    }

    @Transactional
    public Developer updateDevById(String id, Developer devNew)
    {
        if (devNew.name == null) {
            throw new WebApplicationException(Response.status(422).entity(
                    new ErrorEntity(422, "Developer Name was not set on request.")).build());
        }

        Developer dev = Developer.findById(id);
        if (dev == null) {
            throw new WebApplicationException(Response.status(404).entity(
                    new ErrorEntity(404, "Developer with id of " + id + " does not exist.")).build());
        }

        if (devNew.getName() != null) {
            dev.setName(devNew.name);
        }
        if (devNew.getTeam() != null) {
            dev.setTeam(devNew.team);
        }
        if (devNew.getCreatedAt() != null) {
            dev.setCreatedAt(devNew.createdAt);
        }
        if (devNew.getUpdatedAt() != null) {
            dev.setUpdatedAt(devNew.updatedAt);
        }

        //Update Skills
        if (devNew.getSkills() != null) {
            Skill.delete("developer_id = ?1", dev.getId());
            ArrayList<Skill> skillList = new ArrayList<>();

            for (String skillName : devNew.getSkills()) {
                Skill skill = new Skill(skillName);
                skill.setDeveloper(dev);
                dev.skills.add(skill);
                skill.persist();
                System.out.println(dev.toString());
            }
        }

        return devNew;
    }

    public Response removeDevById(String id)
    {
        Developer dev = Developer.findById(id);
        if (dev == null) {
            throw new WebApplicationException(Response.status(404).entity(
                    new ErrorEntity(404, "Developer with id of " + id + " does not exist.")).build());
        }
        dev.delete();
        return Response.status(204).build();
    }
}
