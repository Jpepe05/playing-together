package com.jpepe.playingtogether.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class PlayerRound {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne private Player player;

  @ManyToOne private Round round;

  private String guessAttempt;

  public PlayerRound(Player player, Round round, String guessAttempt) {
    this.player = player;
    this.round = round;
    this.guessAttempt = guessAttempt;
  }
}
