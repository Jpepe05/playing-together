package com.jpepe.playingtogether.service;

import com.jpepe.playingtogether.entity.Category;
import com.jpepe.playingtogether.entity.CategoryWord;
import com.jpepe.playingtogether.entity.Word;
import com.jpepe.playingtogether.mapper.WordsByCategoryResponseVoMapper;
import com.jpepe.playingtogether.repository.CategoryRepository;
import com.jpepe.playingtogether.repository.CategoryWordRepository;
import com.jpepe.playingtogether.repository.WordRepository;
import com.jpepe.playingtogether.vo.request.RoundRequestVo;
import com.jpepe.playingtogether.vo.response.WordsByCategoryResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryWordService {

  private final WordRepository wordRepository;
  private final CategoryRepository categoryRepository;
  private final CategoryWordRepository categoryWordRepository;
  private final WordsByCategoryResponseVoMapper wordsByCategoryResponseVoMapper;

  @Transactional
  public CategoryWord addCategoryWordIfNotExists(String category, String word) {

    var categoryWord = categoryWordRepository.findByCategoryAndWord(category, word);
    if (categoryWord.isPresent()) {
      return categoryWord.get();
    }

    var categoryEntity =
        categoryRepository
            .findById(category)
            .orElse(categoryRepository.save(new Category(category)));
    var wordEntity = wordRepository.findById(word).orElse(wordRepository.save(new Word(word)));

    return categoryWordRepository.save(new CategoryWord(categoryEntity, wordEntity));
  }

  @Transactional
  public List<CategoryWord> addCategoryWordListIfNotExists(List<RoundRequestVo> roundRequestVo) {
    return roundRequestVo.stream()
        .map(
            round ->
                addCategoryWordIfNotExists(
                    round.category().toLowerCase(), round.wordToGuess().toLowerCase()))
        .toList();
  }

  @Transactional(readOnly = true)
  public List<WordsByCategoryResponseVo> findAllWordsGroupedByCategory() {
    var categoryWords = categoryWordRepository.findAllByActiveTrue();
    return wordsByCategoryResponseVoMapper.from(categoryWords);
  }

  @Transactional
  public void addWordsByCategory(String category, Set<String> words) {
    var categoryEntity = categoryRepository.save(new Category(category.toLowerCase()));

    var wordEntities =
        wordRepository.saveAll(
            words.stream().map(String::toLowerCase).map(Word::new).collect(Collectors.toSet()));

    categoryWordRepository.saveAll(
        wordEntities.stream()
            .map(word -> new CategoryWord(categoryEntity, word))
            .collect(Collectors.toSet()));
  }

  public void deleteCategoryWord(String category, String word) {
    var categoryWord = categoryWordRepository.findByCategoryAndWord(category, word);
    if (categoryWord.isPresent()) {
      var categoryWordEntity = categoryWord.get();
      categoryWordEntity.setActive(false);
      categoryWordRepository.save(categoryWordEntity);
    }
  }
}
