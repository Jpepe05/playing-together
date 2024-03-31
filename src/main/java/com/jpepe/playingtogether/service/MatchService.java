package com.jpepe.playingtogether.service;

import com.jpepe.playingtogether.exception.EntityNotFoundException;
import com.jpepe.playingtogether.mapper.MatchMapper;
import com.jpepe.playingtogether.repository.MatchRepository;
import com.jpepe.playingtogether.repository.PlayerRepository;
import com.jpepe.playingtogether.vo.request.MatchFinishedRequestVo;
import com.jpepe.playingtogether.vo.request.PlayerAttemptRequestVo;
import com.jpepe.playingtogether.vo.request.RoundRequestVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class MatchService {

  private final MatchRepository matchRepository;
  private final PlayerRepository playerRepository;
  private final MatchMapper matchMapper;
  private final CategoryWordService categoryWordService;

  @Transactional
  public void finishMatch(MatchFinishedRequestVo matchRequestVo) {

    var categoryWords = categoryWordService.addCategoryWordListIfNotExists(matchRequestVo.rounds());

    var playerIds =
        Stream.concat(getPlayerIds(matchRequestVo), getArtistId(matchRequestVo))
            .collect(Collectors.toSet());

    var players = playerRepository.findAllByIdIn(playerIds);

    if (playerIds.size() != players.size()) {
      throw new EntityNotFoundException("Some players does not have an account");
    }

    var match = matchMapper.from(matchRequestVo, players, categoryWords);

    matchRepository.save(match);
  }

  private static Stream<String> getArtistId(MatchFinishedRequestVo matchRequestVo) {
    return matchRequestVo.rounds().stream().map(RoundRequestVo::artistId);
  }

  private static Stream<String> getPlayerIds(MatchFinishedRequestVo matchRequestVo) {
    return matchRequestVo.rounds().stream()
        .map(RoundRequestVo::players)
        .flatMap(Collection::stream)
        .map(PlayerAttemptRequestVo::playerId);
  }
}
