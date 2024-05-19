package com.jpepe.playingtogether.repository;

import com.jpepe.playingtogether.entity.projection.RoundAttemptDetails;
import com.jpepe.playingtogether.entity.projection.RoundSummary;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class AnalysisRepositoryImpl implements AnalysisRepository {

  @PersistenceContext private EntityManager entityManager;

  @Override
  public List<RoundSummary> gatherRoundSummariesForPlayer(String playerId) {
    var attempts =
        entityManager
            .createQuery(
                """
                            SELECT r.match.id AS matchId,
                                   r.id as roundId,
                                   cw.category.name AS categoryName,
                                   cw.word.name AS wordName,
                                   r.image AS image,
                                   pr.guessAttempt AS guessAttempt,
                                   r.match.matchDate AS matchDate
                            FROM PlayerRound pr
                            JOIN pr.round r
                            JOIN r.categoryWord.id cw
                            WHERE pr.player.id = :playerId
                        """,
                RoundAttemptDetails.class)
            .setParameter("playerId", playerId)
            .getResultList();
    return aggregateAttemptsByRound(attempts);
  }

  private List<RoundSummary> aggregateAttemptsByRound(List<RoundAttemptDetails> attempts) {
    Map<Long, RoundSummary> rounds = new LinkedHashMap<>();

    for (RoundAttemptDetails attempt : attempts) {
      rounds
          .computeIfAbsent(
              attempt.getRoundId(),
              k ->
                  new RoundSummary(
                      attempt.getMatchId(),
                      attempt.getCategoryName(),
                      attempt.getWordName(),
                      attempt.getImage(),
                      new ArrayList<>(),
                      attempt.getMatchDate()))
          .getAttempts()
          .add(attempt.getGuessAttempt());
    }

    return new ArrayList<>(rounds.values());
  }
}
