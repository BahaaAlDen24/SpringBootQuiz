package com.SpringBootQuiz.SpringBootQuiz.SalesTransactionsRevision;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaleTransactionRevisionController {
    @Autowired
    private SaleTransactionRevisionService saleTransactionRevisionService;

    // Single item
    @GetMapping("/SaleTransactionRevisions/{id}")
    Object getSaleTransactionRevisions(@PathVariable Long id) {
        try {
            return saleTransactionRevisionService.getSaleTransactionRevisions(id);
        }catch (Exception e ){
            return e.getMessage() ;
        }
    }
}

