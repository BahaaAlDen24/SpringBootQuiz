package com.SpringBootQuiz.SpringBootQuiz.SalesOperations;

import com.SpringBootQuiz.SpringBootQuiz.Products.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SaleOperationController {
    @Autowired
    private SaleOperationService SaleOperationService;

    // return all SaleOperations in the system
    @GetMapping("/SaleOperations")
    Object all() {
        try {
            return SaleOperationService.all();
        }catch (Exception e ){
            return e.getMessage() ;
        }
    }

    @PostMapping("/SaleOperations")
    Object newSaleOperation(@RequestBody  SaleOperation newSaleOperation) {
        try {
            return SaleOperationService.newSaleOperation(newSaleOperation);
        }catch (Exception e ){
            return e.getMessage() ;
        }
    }

    // Single item
    @GetMapping("/SaleOperations/{id}")
    Object getSaleOperationByID(@PathVariable Long id) {
        try {
            return SaleOperationService.getSaleOperationByID(id);
        }catch (Exception e ){
            return e.getMessage() ;
        }
    }

    @PutMapping("/SaleOperations/{id}")
    Object updateSaleOperation(@RequestBody SaleOperation newSaleOperation, @PathVariable Long id) {
        try {
            return SaleOperationService.updateSaleOperation(newSaleOperation,id);
        }catch (Exception e ){
            return e.getMessage() ;
        }
    }

    @DeleteMapping("/SaleOperations/{id}")
    Object deleteSaleOperation(@PathVariable Long id) {
        try {
            return SaleOperationService.deleteSaleOperation(id);
        }catch (Exception e ){
            return e.getMessage() ;
        }
    }
}
