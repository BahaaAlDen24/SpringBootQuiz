package com.SpringBootQuiz.SpringBootQuiz.ProductsRevision;

import org.springframework.stereotype.Service;

@Service
public class ProductRevisionService {
    private final ProductRevisionRepository repository;

    public ProductRevisionService(ProductRevisionRepository repository) {
        this.repository = repository;
    }

    public Object getProductRevisions(Long ProductID) {
        try {
            return repository.getProductRevisions(ProductID);
        }catch (Exception e ){
            return e.getMessage() ;
        }
    }
}
