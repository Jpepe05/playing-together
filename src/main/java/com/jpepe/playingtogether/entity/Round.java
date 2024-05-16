package com.jpepe.playingtogether.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Entity
@ToString(exclude = "playerRounds")
@Data
public class Round {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(optional = false)
  private Match match;

  @ManyToOne(optional = false)
  private CategoryWord categoryWord;

  @ManyToOne(optional = false)
  private Player artist;

  private String image;

  @OneToMany(mappedBy = "round", cascade = CascadeType.PERSIST)
  private List<PlayerRound> playerRounds;
}
