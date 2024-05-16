package com.jpepe.playingtogether.entity;

import jakarta.persistence.*;
import java.time.Instant;
import lombok.Data;
import lombok.ToString;

import java.util.List;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "game_match")
@Data
@ToString(exclude = "rounds")
public class Match {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Integer roundTime;

  @OneToMany(mappedBy = "match", cascade = CascadeType.PERSIST)
  private List<Round> rounds;

  @CreationTimestamp
  private Instant matchDate;
}
