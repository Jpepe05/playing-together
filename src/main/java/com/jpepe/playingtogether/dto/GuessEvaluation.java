package com.jpepe.playingtogether.dto;

import com.jpepe.playingtogether.similarity.SimilarityResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GuessEvaluation implements Comparable<GuessEvaluation> {
  private String wordToGuess;
  private String attempt;
  private SimilarityResult similarityResult;

  @Override
  public int compareTo(GuessEvaluation o) {
    return this.getSimilarityResult().type().compareTo(o.getSimilarityResult().type());
  }
}
