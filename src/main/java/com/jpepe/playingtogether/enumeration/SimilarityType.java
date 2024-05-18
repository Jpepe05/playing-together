package com.jpepe.playingtogether.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SimilarityType {
  EQUAL,
  TYPOGRAPHICAL_SIMILAR, // Levenshtein
  ACCENTUATION_SIMILAR, // Accentuation
  PHONETIC_SIMILAR, // Metaphone/Soundex
  SEMANTICALLY_RELATED, // GPT
  SAME_CATEGORY, // GPT
  CONTEXTUAL_CONNECTION, // GPT
  OTHER, // GPT
  DIFFERENT
}
