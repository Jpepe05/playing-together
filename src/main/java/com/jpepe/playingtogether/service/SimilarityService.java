package com.jpepe.playingtogether.service;

import com.jpepe.playingtogether.dto.GuessEvaluation;
import com.jpepe.playingtogether.enumeration.SimilarityType;
import com.jpepe.playingtogether.similarity.Similarity;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SimilarityService {

  private final Similarity basicSimilarity;
  private final Similarity enhancedSimilarity;

  @Async
  public CompletableFuture<GuessEvaluation> getBestAttempt(
      String wordToGuess, List<String> attempts) {

    var results =
        attempts.stream()
            .map(
                attempt ->
                    new GuessEvaluation(
                        wordToGuess, attempt, basicSimilarity.check(wordToGuess, attempt)))
            .sorted()
            .toList();

    var bestGuess = results.getFirst();
    var lastAttempt = attempts.getLast();

    var guessEvaluation =
        SimilarityType.DIFFERENT.equals(bestGuess.getSimilarityResult().type())
            ? new GuessEvaluation(
                wordToGuess, lastAttempt, enhancedSimilarity.check(wordToGuess, lastAttempt))
            : bestGuess;

    return CompletableFuture.completedFuture(guessEvaluation);
  }
}
