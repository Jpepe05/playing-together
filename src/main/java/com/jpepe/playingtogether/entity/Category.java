package com.jpepe.playingtogether.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = "categoryWords")
public class Category {

  @Id @EqualsAndHashCode.Include private String name;

  @OneToMany(mappedBy = "id.category", fetch = FetchType.LAZY)
  private Set<CategoryWord> categoryWords;

  public Category(String name) {
    this.name = name;
  }
}
