package com.jpepe.playingtogether.repository;

import com.jpepe.playingtogether.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordRepository extends JpaRepository<Word, String> {}
