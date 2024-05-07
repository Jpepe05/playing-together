package com.jpepe.playingtogether.similarity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.parser.BeanOutputParser;
import org.springframework.ai.parser.OutputParser;
import org.springframework.core.io.Resource;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class GptSimilarity extends Similarity {

  private final OutputParser<SimilarityResult> outputParser =
      new BeanOutputParser<>(SimilarityResult.class);
  private final ChatClient chatClient;
  private final Resource wordSimilarityPrompt;

  private ChatResponse callGptApi(String word1, String word2) {
    var systemPromptTemplate =
        new SystemPromptTemplate(wordSimilarityPrompt)
            .create(Map.of("drawing", word1, "guess", word2, "format", outputParser.getFormat()));
    return chatClient.call(systemPromptTemplate);
  }

  @Override
  public SimilarityResult check(String word1, String word2) {
    try {
      var response = callGptApi(word1, word2);
      return outputParser.parse(response.getResult().getOutput().getContent());
    } catch (Exception ex) {
      log.error("Error fetching similarity from gpt api", ex);
      return checkNext(word1, word2);
    }
  }
}
