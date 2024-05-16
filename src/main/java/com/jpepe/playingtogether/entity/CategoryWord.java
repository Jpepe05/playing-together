package com.jpepe.playingtogether.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = "rounds")
public class CategoryWord {

  @EqualsAndHashCode.Include @EmbeddedId public CategoryWordId id;

  @OneToMany(mappedBy = "categoryWord")
  private Set<Round> rounds = new HashSet<>();

  public CategoryWord(Category category, Word word) {
    this.id = new CategoryWordId(category, word);
  }
}
