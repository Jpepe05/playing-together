package com.jpepe.playingtogether.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Player {
  @Id private Long id;
  private String name;

  @OneToMany(mappedBy = "player")
  private List<PlayerRound> playerRounds;

  @OneToMany(mappedBy = "artist")
  private List<Round> paintedRounds;
}
