package com.jpepe.playingtogether.config;

import com.jpepe.playingtogether.similarity.*;
import org.springframework.ai.chat.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class SimilarityConfiguration {

  @Bean
  public Similarity similarity(
      ChatClient chatClient,
      @Value("classpath:/prompts/word-similarity.st") Resource wordSimilarityPrompt) {
    return Similarity.link(
        new EqualSimilarity(),
        new LevenshteinSimilarity(),
        new MetaphoneSimilarity(),
        new GptSimilarity(chatClient, wordSimilarityPrompt));
  }
}
