package com.jpepe.playingtogether.controller;

import com.jpepe.playingtogether.service.MatchService;
import com.jpepe.playingtogether.vo.request.MatchFinishedRequestVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/matches")
@RequiredArgsConstructor
public class MatchController {

  private final MatchService matchService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void finishMatch(@RequestBody MatchFinishedRequestVo matchRequestVo) {
    matchService.finishMatch(matchRequestVo);
  }
}
