package com.jpepe.playingtogether.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@ToString(exclude = {"playerRounds", "paintedRounds"})
public class Player {
  @Id private String id;
  private String name;
  private boolean isGuardian;

  private Integer age;

  @OneToMany(mappedBy = "player")
  private List<PlayerRound> playerRounds;

  @OneToMany(mappedBy = "artist")
  private List<Round> paintedRounds;

  @Embedded private EducationInfo educationInfo;
  @Embedded private ConditionInfo conditionInfo;
}
