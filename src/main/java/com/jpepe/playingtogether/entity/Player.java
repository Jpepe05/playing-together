package com.jpepe.playingtogether.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Player {
  @Id private String id;
  private String name;
  private boolean isGuardian;
  private int age;

  @OneToMany(mappedBy = "player")
  private List<PlayerRound> playerRounds;

  @OneToMany(mappedBy = "artist")
  private List<Round> paintedRounds;

  @Embedded private EducationInfo educationInfo;
  @Embedded private ConditionInfo conditionInfo;
}
