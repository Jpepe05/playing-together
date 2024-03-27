package com.jpepe.playingtogether.service;

import com.jpepe.playingtogether.entity.Category;
import com.jpepe.playingtogether.entity.CategoryWord;
import com.jpepe.playingtogether.entity.Word;
import com.jpepe.playingtogether.repository.CategoryRepository;
import com.jpepe.playingtogether.repository.CategoryWordRepository;
import com.jpepe.playingtogether.repository.WordRepository;
import com.jpepe.playingtogether.vo.request.RoundRequestVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryWordService {

  private final WordRepository wordRepository;
  private final CategoryRepository categoryRepository;
  private final CategoryWordRepository categoryWordRepository;

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
        .map(round -> addCategoryWordIfNotExists(round.category(), round.wordToGuess()))
        .toList();
  }
}
