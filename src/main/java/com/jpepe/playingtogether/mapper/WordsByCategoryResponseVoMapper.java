package com.jpepe.playingtogether.mapper;

import com.jpepe.playingtogether.entity.Category;
import com.jpepe.playingtogether.entity.CategoryWord;
import com.jpepe.playingtogether.entity.CategoryWordId;
import com.jpepe.playingtogether.entity.Word;
import com.jpepe.playingtogether.vo.response.WordsByCategoryResponseVo;
import org.mapstruct.Mapper;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper
public interface WordsByCategoryResponseVoMapper {

  default List<WordsByCategoryResponseVo> from(Collection<CategoryWord> categoryWordIds) {
    if (CollectionUtils.isEmpty(categoryWordIds)) {
      return Collections.emptyList();
    }

    var wordsGroupedByCategory = getWordsGroupedByCategory(categoryWordIds);

    return getWordsByCategoryResponseVos(wordsGroupedByCategory);
  }

  private static Map<Category, List<Word>> getWordsGroupedByCategory(
      Collection<CategoryWord> categoryWordIds) {
    return categoryWordIds.stream()
        .map(CategoryWord::getId)
        .collect(
            Collectors.groupingBy(
                CategoryWordId::getCategory,
                Collectors.mapping(CategoryWordId::getWord, Collectors.toList())));
  }

  private static List<WordsByCategoryResponseVo> getWordsByCategoryResponseVos(
      Map<Category, List<Word>> wordsGroupedByCategory) {
    return wordsGroupedByCategory.entrySet().stream()
        .map(WordsByCategoryResponseVoMapper::map)
        .toList();
  }

  private static WordsByCategoryResponseVo map(Map.Entry<Category, List<Word>> entry) {
    return new WordsByCategoryResponseVo(
        entry.getKey().getName(),
        entry.getValue().stream().map(Word::getName).collect(Collectors.toList()));
  }
}
