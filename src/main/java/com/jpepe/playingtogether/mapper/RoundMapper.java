package com.jpepe.playingtogether.mapper;

import com.jpepe.playingtogether.entity.*;
import com.jpepe.playingtogether.vo.request.RoundRequestVo;
import org.mapstruct.*;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Mapper
public interface RoundMapper {

  @Mapping(target = "categoryWord", ignore = true)
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "artist", ignore = true)
  @Mapping(target = "playerRounds", ignore = true)
  Round from(
      RoundRequestVo roundRequestVo,
      Match match,
      @Context List<Player> players,
      @Context List<CategoryWord> categoryWordList);

  default List<Round> from(
      List<RoundRequestVo> rounds,
      Match match,
      @Context List<Player> players,
      @Context List<CategoryWord> categoryWordList) {

    if (CollectionUtils.isEmpty(rounds)) {
      return Collections.emptyList();
    }

    return rounds.stream().map(round -> from(round, match, players, categoryWordList)).toList();
  }

  @AfterMapping
  default void fillPlayerRounds(
      @MappingTarget Round round,
      RoundRequestVo roundRequestVo,
      @Context List<Player> players,
      @Context List<CategoryWord> categoryWordList) {

    var playersMap = players.stream().collect(Collectors.toMap(Player::getId, Function.identity()));
    var categoryWordMap =
        categoryWordList.stream()
            .collect(
                Collectors.toMap(
                    categoryWord ->
                        getKey(
                            categoryWord.getId().getCategory().getName(),
                            categoryWord.getId().getWord().getName()),
                    Function.identity()));

    var playerRounds =
        roundRequestVo.players().stream()
            .flatMap(
                playerAttempt ->
                    playerAttempt.attempts().stream()
                        .map(
                            attempt ->
                                new PlayerRound(
                                    playersMap.get(playerAttempt.playerId()), round, attempt)))
            .toList();

    round.setPlayerRounds(playerRounds);
    round.setArtist(playersMap.get(roundRequestVo.artistId()));
    round.setCategoryWord(
        categoryWordMap.get(getKey(roundRequestVo.category(), roundRequestVo.wordToGuess())));
  }

  private static String getKey(String category, String word) {
    return String.join("::", category, word);
  }
}
