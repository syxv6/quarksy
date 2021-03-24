package com.quarksy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Developer
{
    private UUID id;
    private String name;
    private String team;
    private List<String> skills;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Developer()
    {
    }

    public Developer(int i)
    {
        this.id = UUID.randomUUID();
        this.name = "developer" + i;
        this.team = "team" + i;
        skills.add("s0");
        skills.add("s1");
        skills.add("s2");
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Developer(UUID id, String name, String team, ArrayList<String> skills,
                     LocalDateTime createdAt, LocalDateTime updatedAt)
    {
        this.id = id;
        this.name = name;
        this.team = team;
        this.skills = skills;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}
