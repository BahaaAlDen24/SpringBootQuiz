package com.SpringBootQuiz.SpringBootQuiz.SalesOperationsRevision;

import com.SpringBootQuiz.SpringBootQuiz.ProductsRevision.ProductRevisionRepository;
import org.springframework.stereotype.Service;

@Service
public class SaleOperationRevisionService {
    private final SaleOperationRevisionRepository repository;

    public SaleOperationRevisionService(SaleOperationRevisionRepository repository) {
        this.repository = repository;
    }

    public Object getProductRevisions(Long ProductID) {
        try {
            return repository.getSaleOperationRevisions(ProductID);
        }catch (Exception e ){
            return e.getMessage() ;
        }
    }
}
