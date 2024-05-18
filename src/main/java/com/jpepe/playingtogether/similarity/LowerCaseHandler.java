package com.jpepe.playingtogether.similarity;

public class LowerCaseHandler extends Similarity {
  @Override
  public SimilarityResult check(String word1, String word2) {
    return checkNext(word1.toLowerCase(), word2.toLowerCase());
  }
}
