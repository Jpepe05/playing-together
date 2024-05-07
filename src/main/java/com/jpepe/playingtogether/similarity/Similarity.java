package com.jpepe.playingtogether.similarity;

import com.jpepe.playingtogether.enumeration.SimilarityType;

public abstract class Similarity {

  private Similarity next;

  public static Similarity link(Similarity first, Similarity... chain) {
    Similarity head = first;
    for (Similarity nextInChain : chain) {
      head.next = nextInChain;
      head = nextInChain;
    }
    return first;
  }

  public abstract SimilarityResult check(String word1, String word2);

  protected SimilarityResult checkNext(String word1, String word2) {
    if (next == null) {
      return new SimilarityResult(
          SimilarityType.DIFFERENT, "As duas palavras são completamete diferentes");
    }
    return next.check(word1, word2);
  }
}
