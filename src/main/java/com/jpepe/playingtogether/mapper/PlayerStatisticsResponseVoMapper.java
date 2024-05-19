package com.jpepe.playingtogether.mapper;

import com.jpepe.playingtogether.dto.GuessEvaluation;
import com.jpepe.playingtogether.enumeration.SimilarityType;
import com.jpepe.playingtogether.similarity.SimilarityResult;
import com.jpepe.playingtogether.strategies.ScoreStrategy;
import com.jpepe.playingtogether.vo.PerformanceOverTimeVo;
import com.jpepe.playingtogether.vo.response.PlayerStatisticsResponseVo;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PlayerStatisticsResponseVoMapper {

  private final ScoreStrategy scoreStrategy;

  public PlayerStatisticsResponseVo from(
      Map<Long, List<GuessEvaluation>> bestAttemptsByMatchId,
      Map<Long, Instant> matchDateByMatchId) {

    var totalRounds = bestAttemptsByMatchId.values().stream().mapToLong(Collection::size).sum();

    var hitsAndMistakesPartition = partitionByHits(bestAttemptsByMatchId);

    var totalHits = (long) hitsAndMistakesPartition.get(true).size();

    var commonMistakesCount =
        hitsAndMistakesPartition.get(false).stream()
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

    var accuracyRate = totalHits / totalRounds;

    Map<Instant, List<GuessEvaluation>> attemptsByDate =
        groupAttemptsByDate(bestAttemptsByMatchId, matchDateByMatchId);

    var performanceData = calculateScoresByDate(attemptsByDate);

    return new PlayerStatisticsResponseVo(
        totalRounds, accuracyRate, commonMistakesCount, performanceData);
  }

  private List<PerformanceOverTimeVo> calculateScoresByDate(
      Map<Instant, List<GuessEvaluation>> attemptsByDate) {
    return attemptsByDate.entrySet().stream()
        .map(
            entry ->
                new PerformanceOverTimeVo(
                    entry.getKey(), scoreStrategy.calculate(entry.getValue())))
        .sorted()
        .toList();
  }

  private static Map<Instant, List<GuessEvaluation>> groupAttemptsByDate(
      Map<Long, List<GuessEvaluation>> bestAttemptsByMatchId,
      Map<Long, Instant> matchDateByMatchId) {
    return bestAttemptsByMatchId.entrySet().stream()
        .collect(
            Collectors.groupingBy(
                entry -> matchDateByMatchId.get(entry.getKey()).truncatedTo(ChronoUnit.DAYS),
                Collectors.flatMapping(entry -> entry.getValue().stream(), Collectors.toList())));
  }

  private static Map<Boolean, List<SimilarityType>> partitionByHits(
      Map<Long, List<GuessEvaluation>> bestAttemptsByMatchId) {
    return bestAttemptsByMatchId.values().stream()
        .flatMap(Collection::stream)
        .map(GuessEvaluation::getSimilarityResult)
        .collect(
            Collectors.partitioningBy(
                result -> result.type() == SimilarityType.EQUAL,
                Collectors.mapping(SimilarityResult::type, Collectors.toList())));
  }
}
