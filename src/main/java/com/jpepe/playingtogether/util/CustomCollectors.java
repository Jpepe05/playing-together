package com.jpepe.playingtogether.util;

import static java.util.stream.Collectors.toList;

import com.spotify.futures.CompletableFutures;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collector;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomCollectors {

  /**
   * Returns a collector that transforms a stream of map entries where values are lists of
   * CompletableFuture into a CompletableFuture of a map where values are lists of resolved futures.
   *
   * @param <K> the type of keys in the map
   * @param <V> the type of the values inside the CompletableFuture
   * @return a collector that can be used in a stream to perform the transformation
   */
  public static <K, V>
      Collector<Map.Entry<K, List<CompletableFuture<V>>>, ?, CompletableFuture<Map<K, List<V>>>>
          asyncMapCollector() {
    return CompletableFutures.joinMap(
        Map.Entry::getKey,
        entry ->
            CompletableFuture.allOf(entry.getValue().toArray(new CompletableFuture[0]))
                .thenApply(
                    v -> entry.getValue().stream().map(CompletableFuture::join).collect(toList())));
  }
}
