package com.jpepe.playingtogether.service;

import com.jpepe.playingtogether.similarity.SimilarityResult;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.parser.BeanOutputParser;
import org.springframework.ai.parser.OutputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {

  private final ChatClient chatClient;

  @Value("classpath:/prompts/word-similarity.st")
  private Resource wordSimilarityPrompt;

  private final OutputParser<SimilarityResult> outputParser =
      new BeanOutputParser<>(SimilarityResult.class);

  private ChatResponse callGptApi(String word1, String word2) {
    var systemPromptTemplate =
        new SystemPromptTemplate(wordSimilarityPrompt)
            .create(Map.of("drawing", word1, "guess", word2, "format", outputParser.getFormat()));
    return chatClient.call(systemPromptTemplate);
  }

  @Cacheable("similarity")
  public SimilarityResult compareWords(String word1, String word2) {
    log.info("Calling OpenAI API");
    var response = callGptApi(word1, word2);
    return outputParser.parse(response.getResult().getOutput().getContent());
  }
}
