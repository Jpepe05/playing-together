package com.jpepe.playingtogether.similarity;

import com.jpepe.playingtogether.enumeration.SimilarityType;
import org.apache.commons.codec.language.DoubleMetaphone;

public class MetaphoneSimilarity extends Similarity {

  private static final DoubleMetaphone metaphone = new DoubleMetaphone();

  @Override
  public SimilarityResult check(String word1, String word2) {
    if (metaphone.encode(word1).equals(metaphone.encode(word2))) {
      return new SimilarityResult(
          SimilarityType.PHONETIC_SIMILAR,
          "As palavras '"
              + word1
              + "' e '"
              + word2
              + "' soam de maneira similar, indicando que podem ser percebidas como"
              + " equivalentes ao ouvido, mesmo sendo ortograficamente diferentes.");
    }
    return checkNext(word1, word2);
  }
}
