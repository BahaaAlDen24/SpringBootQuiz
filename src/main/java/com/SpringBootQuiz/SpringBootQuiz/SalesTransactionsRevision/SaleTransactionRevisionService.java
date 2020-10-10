package com.SpringBootQuiz.SpringBootQuiz.SalesTransactionsRevision;

import org.springframework.stereotype.Service;

@Service
public class SaleTransactionRevisionService {
    private final SaleTransactionRevisionRepository repository;

    public SaleTransactionRevisionService(SaleTransactionRevisionRepository repository) {
        this.repository = repository;
    }

    public Object getSaleTransactionRevisions(Long SaleTransactionID) {
        try {
            return repository.getSaleTransactionRevisions(SaleTransactionID);
        }catch (Exception e ){
            return e.getMessage() ;
        }
    }
}
