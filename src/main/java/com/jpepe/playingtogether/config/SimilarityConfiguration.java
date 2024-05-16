package com.jpepe.playingtogether.config;

import com.jpepe.playingtogether.service.ChatService;
import com.jpepe.playingtogether.similarity.EqualSimilarity;
import com.jpepe.playingtogether.similarity.GptSimilarity;
import com.jpepe.playingtogether.similarity.LevenshteinSimilarity;
import com.jpepe.playingtogether.similarity.MetaphoneSimilarity;
import com.jpepe.playingtogether.similarity.Similarity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SimilarityConfiguration {

  @Bean
  public Similarity similarity(ChatService chatService) {
    return Similarity.link(
        new EqualSimilarity(),
        new LevenshteinSimilarity(),
        new MetaphoneSimilarity(),
        new GptSimilarity(chatService));
  }
}
