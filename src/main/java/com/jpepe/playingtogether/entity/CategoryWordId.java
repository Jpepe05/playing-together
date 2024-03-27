package com.jpepe.playingtogether.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryWordId implements Serializable {

  @ManyToOne @EqualsAndHashCode.Include private Category category;

  @ManyToOne @EqualsAndHashCode.Include private Word word;
}
