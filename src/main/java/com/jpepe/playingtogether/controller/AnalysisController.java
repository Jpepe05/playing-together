package com.jpepe.playingtogether.controller;

import com.jpepe.playingtogether.service.AnalysisService;
import com.jpepe.playingtogether.similarity.SimilarityResult;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Hidden
@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class AnalysisController {

  private final AnalysisService analysisService;

  @GetMapping
  public SimilarityResult test(@RequestParam String word1, @RequestParam String word2) {
    return analysisService.compare(word1, word2);
  }
}
