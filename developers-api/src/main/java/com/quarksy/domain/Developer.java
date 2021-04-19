package com.quarksy.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.Id;

@lombok.Data
@Entity
public class Developer extends PanacheEntityBase {
  @Id
  public String id;
  public String name;
  public String team;
  //    public List<String> skills;
  public LocalDateTime createdAt;
  public LocalDateTime updatedAt;
}
