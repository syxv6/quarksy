package com.quarksy.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

@lombok.Data
@Entity
public class Skill extends PanacheEntity {

    @ManyToOne
    @JsonIgnore
    public Developer developer;

    public String skill;




}
