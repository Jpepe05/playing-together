package com.jpepe.playingtogether.repository;

import com.jpepe.playingtogether.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {}
