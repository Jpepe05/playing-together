package com.jpepe.playingtogether.similarity;

import com.jpepe.playingtogether.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class GptSimilarity extends Similarity {

  private final ChatService chatService;

  @Override
  public SimilarityResult check(String word1, String word2) {
    try {
      return chatService.compareWords(word1, word2);
    } catch (Exception ex) {
      log.error("Error fetching similarity from gpt api", ex);
      return checkNext(word1, word2);
    }
  }
}
