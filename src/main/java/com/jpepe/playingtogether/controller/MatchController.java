package com.jpepe.playingtogether.controller;

import com.jpepe.playingtogether.service.MatchService;
import com.jpepe.playingtogether.vo.request.MatchFinishedRequestVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/matches")
@RequiredArgsConstructor
public class MatchController {

  private final MatchService matchService;

  @PostMapping
  public void finishMatch(@RequestBody MatchFinishedRequestVo matchRequestVo) {
    matchService.finishMatch(matchRequestVo);
  }
}
