package com.jpepe.playingtogether.entity.projection;

import java.time.Instant;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoundSummary {

  private Long matchId;
  private String category;
  private String word;
  private String image;
  private List<String> attempts;
  private Instant matchDate;
}
