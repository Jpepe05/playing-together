package com.jpepe.playingtogether.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "game_match")
@Data
public class Match {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Integer roundTime;

  @OneToMany(mappedBy = "match", cascade = CascadeType.PERSIST)
  private List<Round> rounds;
}
