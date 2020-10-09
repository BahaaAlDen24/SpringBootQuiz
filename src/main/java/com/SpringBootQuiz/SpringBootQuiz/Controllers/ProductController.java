package com.SpringBootQuiz.SpringBootQuiz.Controllers;

import com.SpringBootQuiz.SpringBootQuiz.Models.Product;
import com.SpringBootQuiz.SpringBootQuiz.Repositories.ProductRepository;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {
    private final ProductRepository repository;

    ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    // return all Products in the system
    @GetMapping("/Products")
    Object all() {
        try {
            return repository.findAll();
        }catch (Exception e ){
            return e.getMessage() ;
        }
    }

    @PostMapping("/Products")
    Object newProduct(@RequestBody Product newProduct) {
        try {
            return repository.save(newProduct);
        }catch (Exception e ){
            return e.getMessage() ;
        }
    }

    // Single item
    @GetMapping("/Products/{id}")
    Object getProductByID(@PathVariable Long id) {
        try {
            return repository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Could not find Product : " + id .toString()));
        }catch (Exception e ){
            return e.getMessage() ;
        }
    }

    @PutMapping("/Products/{id}")
    Object replaceProduct(@RequestBody Product newProduct, @PathVariable Long id) {
        try {
            return repository.findById(id)
                    .map(Product -> {
                        Product.setName(newProduct.getName());
                        Product.setDescription(newProduct.getDescription());
                        Product.setCategory(newProduct.getCategory());
                        Product.setPrice(newProduct.getPrice());
                        Product.setQuantity(newProduct.getQuantity());
                        return repository.save(Product);
                    })
                    .orElseThrow(() -> new RuntimeException("Could not find Product : " + id .toString()));
        }catch (Exception e ){
            return e.getMessage() ;
        }
    }

    @DeleteMapping("/Products/{id}")
    void deleteProduct(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
