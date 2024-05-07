package com.jpepe.playingtogether.similarity;

import com.jpepe.playingtogether.enumeration.SimilarityType;
import edu.uniba.di.lacam.kdde.lexical_db.ILexicalDatabase;
import edu.uniba.di.lacam.kdde.lexical_db.MITWordNet;
import edu.uniba.di.lacam.kdde.ws4j.RelatednessCalculator;
import edu.uniba.di.lacam.kdde.ws4j.similarity.JiangConrath;
import edu.uniba.di.lacam.kdde.ws4j.util.WS4JConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;

@Slf4j
public class WordNetSimilarity extends Similarity {

  private static final RelatednessCalculator rcs;

  static {
    WS4JConfiguration.getInstance().setMemoryDB(false);
    WS4JConfiguration.getInstance().setMFS(true);
    ILexicalDatabase db = new MITWordNet();
    rcs = new JiangConrath(db);
  }

  @Override
  public SimilarityResult check(String word1, String word2) {
    var watch = StopWatch.createStarted();
    var similarity = rcs.calcRelatednessOfWords(word1, word2);
    log.info("Took {}ms to calculate relatedness of words using JianConrath", watch.getTime());
    if (similarity > 0.5) {
      return new SimilarityResult(
          SimilarityType.SEMANTICALLY_RELATED,
          "As palavras '"
              + word1
              + "' e '"
              + word2
              + "' compartilham um significado semelhante ou estão relacionadas em um contexto"
              + " semântico, como sinônimos ou conceitos que estão frequentemente associados.");
    }
    return checkNext(word1, word2);
  }
}
