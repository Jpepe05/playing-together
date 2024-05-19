package com.jpepe.playingtogether.service;

import static com.jpepe.playingtogether.util.CustomCollectors.asyncMapCollector;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import com.jpepe.playingtogether.dto.GuessEvaluation;
import com.jpepe.playingtogether.entity.projection.RoundSummary;
import com.jpepe.playingtogether.exception.EntityNotFoundException;
import com.jpepe.playingtogether.mapper.PlayerStatisticsResponseVoMapper;
import com.jpepe.playingtogether.repository.AnalysisRepository;
import com.jpepe.playingtogether.repository.PlayerRepository;
import com.jpepe.playingtogether.similarity.Similarity;
import com.jpepe.playingtogether.similarity.SimilarityResult;
import com.jpepe.playingtogether.vo.response.PlayerStatisticsResponseVo;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnalysisService {

  private final Similarity enhancedSimilarity;
  private final PlayerRepository playerRepository;
  private final AnalysisRepository analysisRepository;
  private final SimilarityService similarityService;
  private final PlayerStatisticsResponseVoMapper playerStatisticsResponseVoMapper;

  public SimilarityResult compare(String word1, String word2) {
    return enhancedSimilarity.check(word1, word2);
  }

  public PlayerStatisticsResponseVo getPlayerStatistics(String playerId) {
    if (!playerRepository.existsById(playerId)) {
      throw new EntityNotFoundException("Player not found");
    }

    var playerRoundSummaries = analysisRepository.gatherRoundSummariesForPlayer(playerId);
    var bestAttempts = getBestAttemptsByMatchId(playerRoundSummaries);

    return playerStatisticsResponseVoMapper.from(
        bestAttempts, getMatchDateByMatchId(playerRoundSummaries));
  }

  private static Map<Long, Instant> getMatchDateByMatchId(List<RoundSummary> playerRoundSummaries) {
    return playerRoundSummaries.stream()
        .collect(
            toMap(
                RoundSummary::getMatchId,
                RoundSummary::getMatchDate,
                (existing, replacement) -> existing));
  }

  @SneakyThrows
  private Map<Long, List<GuessEvaluation>> getBestAttemptsByMatchId(
      List<RoundSummary> playerRoundSummaries) {

    var bestAttemptsByMatchId =
        playerRoundSummaries.stream()
            .collect(groupingBy(RoundSummary::getMatchId, mapping(this::getBestAttempt, toList())));

    return invertToAsyncMap(bestAttemptsByMatchId).get();
  }

  private static CompletableFuture<Map<Long, List<GuessEvaluation>>> invertToAsyncMap(
      Map<Long, List<CompletableFuture<GuessEvaluation>>> bestAttemptsByMatchId) {
    return bestAttemptsByMatchId.entrySet().stream().collect(asyncMapCollector());
  }

  private CompletableFuture<GuessEvaluation> getBestAttempt(RoundSummary roundSummary) {
    return similarityService.getBestAttempt(roundSummary.getWord(), roundSummary.getAttempts());
  }
}
