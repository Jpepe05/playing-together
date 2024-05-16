package com.jpepe.playingtogether.similarity;

import com.jpepe.playingtogether.enumeration.SimilarityType;

public class EqualSimilarity extends Similarity {
  @Override
  public SimilarityResult check(String word1, String word2) {
    if (word1.equalsIgnoreCase(word2)) {
      return new SimilarityResult(
          SimilarityType.EQUAL,
          "As palavras são idênticas. Ambas são '"
              + word1
              + "', indicando uma correspondência exata sem variações.");
    }
    return checkNext(word1.toLowerCase(), word2.toLowerCase());
  }
}
