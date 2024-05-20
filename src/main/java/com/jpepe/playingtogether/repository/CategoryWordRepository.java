package com.jpepe.playingtogether.repository;

import com.jpepe.playingtogether.entity.CategoryWord;
import com.jpepe.playingtogether.entity.CategoryWordId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CategoryWordRepository extends JpaRepository<CategoryWord, CategoryWordId> {

  @Query("SELECT cw FROM CategoryWord cw WHERE cw.id.category.name = ?1 AND cw.id.word.name = ?2 ")
  Optional<CategoryWord> findByCategoryAndWord(String category, String word);

  List<CategoryWord> findAllByActiveTrue();
}
