package com.jpepe.playingtogether.controller;

import com.jpepe.playingtogether.service.PlayerService;
import com.jpepe.playingtogether.vo.request.PlayerCreateRequestVo;
import com.jpepe.playingtogether.vo.response.PlayerResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/players")
@RequiredArgsConstructor
public class PlayerController {

  private final PlayerService playerService;

  @PostMapping
  public void createPlayer(@RequestBody PlayerCreateRequestVo playerVo) {
    playerService.createPlayer(playerVo);
  }

  @GetMapping("/{playerId}")
  public PlayerResponseVo getPlayerInfo(@PathVariable String playerId) {
    return playerService.getPlayerInfo(playerId);
  }
}
