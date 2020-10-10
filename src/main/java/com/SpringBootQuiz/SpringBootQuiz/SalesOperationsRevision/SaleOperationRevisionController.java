package com.SpringBootQuiz.SpringBootQuiz.SalesOperationsRevision;

import com.SpringBootQuiz.SpringBootQuiz.ProductsRevision.ProductRevisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaleOperationRevisionController {
    @Autowired
    private SaleOperationRevisionService saleOperationRevisionService;

    // Single item
    @GetMapping("/SaleOperationRevisions/{id}")
    Object getProductRevisions(@PathVariable Long id) {
        try {
            return saleOperationRevisionService.getProductRevisions(id);
        }catch (Exception e ){
            return e.getMessage() ;
        }
    }
}
