package com.SpringBootQuiz.SpringBootQuiz.Products;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product , Long> {
}
