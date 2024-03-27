package com.jpepe.playingtogether.service;

import com.jpepe.playingtogether.mapper.PlayerMapper;
import com.jpepe.playingtogether.repository.PlayerRepository;
import com.jpepe.playingtogether.vo.request.PlayerCreateRequestVo;
import com.jpepe.playingtogether.vo.response.PlayerResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlayerService {

  private final PlayerRepository playerRepository;
  private final PlayerMapper playerMapper;

  public void createPlayer(PlayerCreateRequestVo playerVo) {
    var player = playerMapper.from(playerVo);
    playerRepository.save(player);
  }

  public PlayerResponseVo getPlayerInfo(Long playerId) {
    var player = playerRepository.findById(playerId).orElseThrow(RuntimeException::new);
    return playerMapper.to(player);
  }
}
