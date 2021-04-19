package com.quarksy.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

@lombok.Data
@Entity
public class Developer extends PanacheEntityBase
{
    @Id
    public String id;
    public String name;
    public String team;

//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
//    @JoinColumn(name = "developer_id")
//    public List<Skill> skills;

    // See guide at https://quarkus.io/guides/hibernate-search-orm-elasticsearch
    @OneToMany(mappedBy = "skill", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    public List<Skill> skills;


    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ssXXX")
    public ZonedDateTime createdAt;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ssXXX")
    public ZonedDateTime updatedAt;
}
