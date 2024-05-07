package com.jpepe.playingtogether.mapper;

import com.jpepe.playingtogether.entity.Player;
import com.jpepe.playingtogether.vo.request.PlayerCreateRequestVo;
import com.jpepe.playingtogether.vo.request.PlayerUpdateRequestVo;
import com.jpepe.playingtogether.vo.response.PlayerResponseVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PlayerMapper {

  @Mapping(target = "paintedRounds", ignore = true)
  @Mapping(target = "playerRounds", ignore = true)
  @Mapping(source = "isGuardian", target = "guardian")
  Player from(PlayerCreateRequestVo playerVo);

  @Mapping(target = "isGuardian", source = "guardian")
  PlayerResponseVo to(Player player);

  @Mapping(target = "name", source = "playerVo.name")
  @Mapping(target = "age", source = "playerVo.age")
  Player from(Player player, PlayerUpdateRequestVo playerVo);
}
