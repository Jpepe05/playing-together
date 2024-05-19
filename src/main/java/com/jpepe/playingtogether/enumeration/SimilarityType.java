package com.jpepe.playingtogether.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SimilarityType {
  EQUAL,
  ACCENTUATION_SIMILAR, // Accentuation
  TYPOGRAPHICAL_SIMILAR, // Levenshtein
  PHONETIC_SIMILAR, // Metaphone/Soundex
  SEMANTICALLY_RELATED, // GPT
  SAME_CATEGORY, // GPT
  CONTEXTUAL_CONNECTION, // GPT
  OTHER, // GPT
  DIFFERENT
}
