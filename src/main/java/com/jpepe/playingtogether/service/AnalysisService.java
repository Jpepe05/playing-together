package com.jpepe.playingtogether.service;

import com.jpepe.playingtogether.similarity.Similarity;
import com.jpepe.playingtogether.similarity.SimilarityResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnalysisService {

  private final Similarity similarity;

  public SimilarityResult compare(String word1, String word2) {
    return similarity.check(word1, word2);
  }
}
