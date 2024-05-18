package com.jpepe.playingtogether.similarity;

import com.jpepe.playingtogether.enumeration.SimilarityType;
import org.apache.commons.lang3.StringUtils;

public class AccentuationSimilarity extends Similarity {
  @Override
  public SimilarityResult check(String word1, String word2) {
    String normalizedWord1 = StringUtils.stripAccents(word1);
    String normalizedWord2 = StringUtils.stripAccents(word2);

    if (normalizedWord1.equalsIgnoreCase(normalizedWord2)) {
      return new SimilarityResult(
          SimilarityType.ACCENTUATION_SIMILAR,
          "As palavras são similares, diferindo apenas em acentuação. '"
              + word1
              + "' e '"
              + word2
              + "' são percebidas como equivalentes sem os acentos.");
    }
    return checkNext(word1, word2);
  }
}
