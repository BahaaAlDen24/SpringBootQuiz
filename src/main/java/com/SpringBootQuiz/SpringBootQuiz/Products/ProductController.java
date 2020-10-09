package com.SpringBootQuiz.SpringBootQuiz.Products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    @Autowired
    private ProductService ProductService;

    // return all Products in the system
    @GetMapping("/Products")
    Object all() {
        try {
            return ProductService.all();
        }catch (Exception e ){
            return e.getMessage() ;
        }
    }

    @PostMapping("/Products")
    Object newProduct(@RequestBody Product newProduct) {
        try {
            return ProductService.newProduct(newProduct);
        }catch (Exception e ){
            return e.getMessage() ;
        }
    }

    // Single item
    @GetMapping("/Products/{id}")
    Object getProductByID(@PathVariable Long id) {
        try {
            return ProductService.getProductByID(id);
        }catch (Exception e ){
            return e.getMessage() ;
        }
    }

    @PutMapping("/Products/{id}")
    Object updateProduct(@RequestBody Product newProduct, @PathVariable Long id) {
        try {
            return ProductService.updateProduct(newProduct,id);
        }catch (Exception e ){
            return e.getMessage() ;
        }
    }

    @DeleteMapping("/Products/{id}")
    Object deleteProduct(@PathVariable Long id) {
        try {
            return ProductService.deleteProduct(id);
        }catch (Exception e ){
            return e.getMessage() ;
        }
    }
}
