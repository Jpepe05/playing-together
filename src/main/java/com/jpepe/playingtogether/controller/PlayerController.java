package com.jpepe.playingtogether.controller;

import com.jpepe.playingtogether.service.AnalysisService;
import com.jpepe.playingtogether.service.PlayerService;
import com.jpepe.playingtogether.vo.request.PlayerEducationHealthRequestVo;
import com.jpepe.playingtogether.vo.request.PlayerCreateRequestVo;
import com.jpepe.playingtogether.vo.request.PlayerUpdateRequestVo;
import com.jpepe.playingtogether.vo.response.PlayerResponseVo;
import com.jpepe.playingtogether.vo.response.PlayerStatisticsResponseVo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/players")
@RequiredArgsConstructor
public class PlayerController {

  private final PlayerService playerService;
  private final AnalysisService analysisService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void createPlayer(@RequestBody @Valid PlayerCreateRequestVo playerVo) {
    playerService.createPlayer(playerVo);
  }

  @PutMapping("/{playerId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void updatePlayer(
      @PathVariable String playerId, @Valid @RequestBody PlayerUpdateRequestVo playerVo) {
    playerService.updatePlayer(playerId, playerVo);
  }

  @GetMapping("/{playerId}")
  public PlayerResponseVo getPlayerInfo(@PathVariable String playerId) {
    return playerService.getPlayerInfo(playerId);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PatchMapping("/{playerId}")
  public void updatePlayerInfo(
      @PathVariable String playerId,
      @RequestBody PlayerEducationHealthRequestVo playerEducationHealthRequestVo) {
    playerService.updatePlayerProfile(playerId, playerEducationHealthRequestVo);
  }

  @GetMapping("/{playerId}/statistics")
  public PlayerStatisticsResponseVo getPlayerStatistics(@PathVariable String playerId) {
    return analysisService.getPlayerStatistics(playerId);
  }
}
