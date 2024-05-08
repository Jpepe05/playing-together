package com.jpepe.playingtogether.controller;

import com.jpepe.playingtogether.service.CategoryWordService;
import com.jpepe.playingtogether.vo.response.WordsByCategoryResponseVo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

  private final CategoryWordService categoryWordService;

  @GetMapping("/words")
  public List<WordsByCategoryResponseVo> findAllWordsGroupedByCategory() {
    return categoryWordService.findAllWordsGroupedByCategory();
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping("/{category}")
  public void addWords(
      @PathVariable @NotBlank String category, @RequestBody @NotEmpty Set<@NotBlank String> words) {
    categoryWordService.addWordsByCategory(category, words);
  }
}
