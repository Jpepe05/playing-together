package com.jpepe.playingtogether.similarity;

import com.jpepe.playingtogether.enumeration.SimilarityType;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.similarity.LevenshteinDistance;

public class LevenshteinSimilarity extends Similarity {
  @Override
  public SimilarityResult check(String word1, String word2) {
    String normalizedWord1 = StringUtils.stripAccents(word1);
    String normalizedWord2 = StringUtils.stripAccents(word2);

    int distance = LevenshteinDistance.getDefaultInstance().apply(normalizedWord1, normalizedWord2);
    if (distance <= 2) {
      return new SimilarityResult(
          SimilarityType.TYPOGRAPHICAL_SIMILAR,
          "As palavras apresentam uma pequena variação ortográfica. '"
              + word1
              + "' e '"
              + word2
              + "' diferem por apenas alguns caracteres, sugerindo um possível erro de"
              + " digitação ou variação menor na escrita.");
    }
    return checkNext(word1, word2);
  }
}
