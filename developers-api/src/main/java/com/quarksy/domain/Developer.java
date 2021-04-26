package com.quarksy.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

@lombok.Data
@Entity
public class Developer extends PanacheEntityBase
{
    @Id
    public String id;
    public String name;
    public String team;

    // See guide at https://quarkus.io/guides/hibernate-search-orm-elasticsearch
    @OneToMany(mappedBy = "developer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    public List<Skill> skills;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ssXXX")
    public ZonedDateTime createdAt;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ssXXX")
    public ZonedDateTime updatedAt;

    @JsonProperty("skills")
    public List<String> getSkills() {
        return this.skills
                .stream()
                .map(Skill::getSkill)
                .collect(toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Developer developer = (Developer) o;
        return Objects.equals(id, developer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Developer{");
        sb.append("id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", team='").append(team).append('\'');
        sb.append(", skills=").append(skills);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append('}');
        return sb.toString();
    }
}
