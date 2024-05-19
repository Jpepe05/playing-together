package com.jpepe.playingtogether.repository;

import com.jpepe.playingtogether.entity.projection.RoundSummary;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalysisRepository {
  List<RoundSummary> gatherRoundSummariesForPlayer(String playerId);
}
