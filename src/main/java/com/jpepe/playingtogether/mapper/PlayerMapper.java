package com.jpepe.playingtogether.mapper;

import com.jpepe.playingtogether.entity.Player;
import com.jpepe.playingtogether.vo.request.PlayerCreateRequestVo;
import com.jpepe.playingtogether.vo.response.PlayerResponseVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PlayerMapper {

  @Mapping(target = "paintedRounds", ignore = true)
  @Mapping(target = "playerRounds", ignore = true)
  Player from(PlayerCreateRequestVo playerVo);

  PlayerResponseVo to(Player player);
}
