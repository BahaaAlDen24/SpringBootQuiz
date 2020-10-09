package com.SpringBootQuiz.SpringBootQuiz.Products;

import com.SpringBootQuiz.SpringBootQuiz.Products.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product , Long> {
}
