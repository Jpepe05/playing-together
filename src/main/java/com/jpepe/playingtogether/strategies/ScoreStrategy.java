package com.jpepe.playingtogether.strategies;

import com.jpepe.playingtogether.dto.GuessEvaluation;
import java.util.List;

public interface ScoreStrategy {

  double calculate(List<GuessEvaluation> evaluations);
}
