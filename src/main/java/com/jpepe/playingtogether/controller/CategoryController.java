package com.jpepe.playingtogether.controller;

import com.jpepe.playingtogether.service.CategoryWordService;
import com.jpepe.playingtogether.vo.response.WordsByCategoryResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

  private final CategoryWordService categoryWordService;

  @GetMapping("/words")
  public List<WordsByCategoryResponseVo> findAllWordsGroupedByCategory() {
    return categoryWordService.findAllWordsGroupedByCategory();
  }
}
