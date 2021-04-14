package com.quarksy.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@lombok.Data
@Entity
public class Developer
{
    @Id
    private UUID id;
    private String name;
    private String team;
    //    private List<String> skills;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
