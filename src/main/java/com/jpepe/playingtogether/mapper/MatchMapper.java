package com.jpepe.playingtogether.mapper;

import com.jpepe.playingtogether.entity.CategoryWord;
import com.jpepe.playingtogether.entity.Match;
import com.jpepe.playingtogether.entity.Player;
import com.jpepe.playingtogether.vo.request.MatchFinishedRequestVo;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper
public abstract class MatchMapper {

  private RoundMapper roundMapper;

  @Autowired
  public void setRoundMapper(RoundMapper roundMapper) {
    this.roundMapper = roundMapper;
  }

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "rounds", ignore = true)
  public abstract Match from(
      MatchFinishedRequestVo matchRequestVo,
      @Context List<Player> players,
      @Context List<CategoryWord> categoryWord);

  @AfterMapping
  protected void fillRounds(
      MatchFinishedRequestVo matchRequestVo,
      @MappingTarget Match match,
      @Context List<Player> players,
      @Context List<CategoryWord> categoryWordList) {

    if (matchRequestVo == null) {
      return;
    }
    var rounds = roundMapper.from(matchRequestVo.rounds(), match, players, categoryWordList);
    match.setRounds(rounds);
  }
}
