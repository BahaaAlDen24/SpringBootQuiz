package com.SpringBootQuiz.SpringBootQuiz.ProductsRevision;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductRevisionController {
    @Autowired
    private ProductRevisionService productRevisionService;

    // Single item
    @GetMapping("/ProductRevisions/{id}")
    Object getProductRevisions(@PathVariable Long id) {
        try {
            return productRevisionService.getProductRevisions(id);
        }catch (Exception e ){
            return e.getMessage() ;
        }
    }
}
