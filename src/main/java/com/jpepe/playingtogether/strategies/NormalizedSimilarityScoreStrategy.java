package com.jpepe.playingtogether.strategies;

import com.jpepe.playingtogether.dto.GuessEvaluation;
import java.util.List;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class NormalizedSimilarityScoreStrategy implements ScoreStrategy {
  @Override
  public double calculate(List<GuessEvaluation> evaluations) {
    double score =
        evaluations.stream()
            .mapToDouble(
                eval ->
                    switch (eval.getSimilarityResult().type()) {
                      case EQUAL -> 2.0;
                      case ACCENTUATION_SIMILAR, ORTHOGRAPHIC_SIMILAR -> 1.0;
                      case PHONETIC_SIMILAR,
                              SEMANTICALLY_RELATED,
                              SAME_CATEGORY,
                              CONTEXTUAL_CONNECTION ->
                          0.5;
                      case DIFFERENT -> -1.0;
                      default -> 0.0;
                    })
            .sum();
    return (score / (evaluations.size() * 2)) * 100;
  }
}
