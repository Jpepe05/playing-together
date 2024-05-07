package com.jpepe.playingtogether.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SimilarityType {
  EQUAL,
  TYPOGRAPHICAL_SIMILAR, // Levenshtein
  PHONETIC_SIMILAR,
  SEMANTICALLY_RELATED, // WordNet
  SAME_CATEGORY, // GPT
  CONTEXTUAL_CONNECTION, // GPT
  OTHER, // GPT
  DIFFERENT
}
