package com.jpepe.playingtogether.entity.projection;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoundAttemptDetails {

  private Long matchId;
  private Long roundId;
  private String categoryName;
  private String wordName;
  private String image;
  private String guessAttempt;
  private Instant matchDate;
}
