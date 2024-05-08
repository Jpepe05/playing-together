package com.jpepe.playingtogether.service;

import com.jpepe.playingtogether.exception.EntityAlreadyExistsException;
import com.jpepe.playingtogether.exception.EntityNotFoundException;
import com.jpepe.playingtogether.mapper.PlayerMapper;
import com.jpepe.playingtogether.repository.PlayerRepository;
import com.jpepe.playingtogether.vo.request.PlayerCreateRequestVo;
import com.jpepe.playingtogether.vo.request.PlayerEducationHealthRequestVo;
import com.jpepe.playingtogether.vo.request.PlayerUpdateRequestVo;
import com.jpepe.playingtogether.vo.response.PlayerResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlayerService {

  private final PlayerRepository playerRepository;
  private final PlayerMapper playerMapper;

  @Transactional
  public void createPlayer(PlayerCreateRequestVo playerVo) {
    if (playerRepository.existsById(playerVo.id())) {
      throw new EntityAlreadyExistsException(
          String.format("Player %s already exists", playerVo.id()));
    }

    var player = playerMapper.from(playerVo);
    playerRepository.save(player);
  }

  @Transactional
  public void updatePlayer(String playerId, PlayerUpdateRequestVo playerVo) {
    var player = playerRepository.findById(playerId).orElseThrow(EntityNotFoundException::new);
    var updatedPlayer = playerMapper.update(player, playerVo);
    playerRepository.save(updatedPlayer);
  }

  @Transactional(readOnly = true)
  public PlayerResponseVo getPlayerInfo(String playerId) {
    var player = playerRepository.findById(playerId).orElseThrow(EntityNotFoundException::new);
    return playerMapper.to(player);
  }

  @Transactional
  public void updatePlayerProfile(String playerId, PlayerEducationHealthRequestVo playerVo) {
    var player = playerRepository.findById(playerId).orElseThrow(EntityNotFoundException::new);
    var updatedPlayer = playerMapper.update(player, playerVo);
    playerRepository.save(updatedPlayer);
  }
}
