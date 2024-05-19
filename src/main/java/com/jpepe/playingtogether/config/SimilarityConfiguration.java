package com.jpepe.playingtogether.config;

import com.jpepe.playingtogether.service.ChatService;
import com.jpepe.playingtogether.similarity.AccentuationSimilarity;
import com.jpepe.playingtogether.similarity.EqualSimilarity;
import com.jpepe.playingtogether.similarity.GptSimilarity;
import com.jpepe.playingtogether.similarity.LevenshteinSimilarity;
import com.jpepe.playingtogether.similarity.LowerCaseHandler;
import com.jpepe.playingtogether.similarity.MetaphoneSimilarity;
import com.jpepe.playingtogether.similarity.Similarity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SimilarityConfiguration {

  @Bean
  public Similarity enhancedSimilarity(ChatService chatService) {
    return Similarity.link(
        new LowerCaseHandler(),
        new EqualSimilarity(),
        new AccentuationSimilarity(),
        new LevenshteinSimilarity(),
        new MetaphoneSimilarity(),
        new GptSimilarity(chatService));
  }

  @Bean
  public Similarity basicSimilarity() {
    return Similarity.link(
        new LowerCaseHandler(),
        new EqualSimilarity(),
        new AccentuationSimilarity(),
        new LevenshteinSimilarity(),
        new MetaphoneSimilarity());
  }
}
