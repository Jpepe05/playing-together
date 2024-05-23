package com.jpepe.playingtogether.mapper;

import com.jpepe.playingtogether.entity.Player;
import com.jpepe.playingtogether.vo.request.PlayerCreateRequestVo;
import com.jpepe.playingtogether.vo.request.PlayerEducationHealthRequestVo;
import com.jpepe.playingtogether.vo.request.PlayerUpdateRequestVo;
import com.jpepe.playingtogether.vo.response.PlayerResponseVo;
import java.util.Collection;
import java.util.List;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueMappingStrategy;

@Mapper
public interface PlayerMapper {

  @Mapping(target = "age", ignore = true)
  @Mapping(target = "educationInfo", ignore = true)
  @Mapping(target = "conditionInfo", ignore = true)
  @Mapping(target = "paintedRounds", ignore = true)
  @Mapping(target = "playerRounds", ignore = true)
  @Mapping(source = "isGuardian", target = "guardian")
  Player from(PlayerCreateRequestVo playerVo);

  @Mapping(target = "isGuardian", source = "guardian")
  PlayerResponseVo to(Player player);

  @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
  List<PlayerResponseVo> to(Collection<Player> players);

  @Mapping(target = "age", ignore = true)
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "guardian", ignore = true)
  @Mapping(target = "educationInfo", ignore = true)
  @Mapping(target = "conditionInfo", ignore = true)
  @Mapping(target = "paintedRounds", ignore = true)
  @Mapping(target = "playerRounds", ignore = true)
  Player update(@MappingTarget Player player, PlayerUpdateRequestVo playerVo);

  @Mapping(target = "name", ignore = true)
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "guardian", ignore = true)
  @Mapping(target = "paintedRounds", ignore = true)
  @Mapping(target = "playerRounds", ignore = true)
  Player update(@MappingTarget Player player, PlayerEducationHealthRequestVo playerVo);
}
