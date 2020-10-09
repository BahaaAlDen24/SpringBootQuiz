package com.SpringBootQuiz.SpringBootQuiz.Repositories;

import com.SpringBootQuiz.SpringBootQuiz.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product , Long> {
}
