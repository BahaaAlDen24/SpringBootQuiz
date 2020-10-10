package com.SpringBootQuiz.SpringBootQuiz.SaleTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SaleTransactionController {
    @Autowired
    private SaleTransactionService SaleTransactionService;

    // return all SaleTransactions in the system
    @GetMapping("/SaleTransactions")
    Object all() {
        try {
            return SaleTransactionService.all();
        }catch (Exception e ){
            return e.getMessage() ;
        }
    }

    @PostMapping("/SaleTransactions")
    Object newSaleTransaction(@RequestBody SaleTransaction newSaleTransaction) {
        try {
            return SaleTransactionService.newSaleTransaction(newSaleTransaction);
        }catch (Exception e ){
            return e.getMessage() ;
        }
    }

    // Single item
    @GetMapping("/SaleTransactions/{id}")
    Object getSaleTransactionByID(@PathVariable Long id) {
        try {
            return SaleTransactionService.getSaleTransactionByID(id);
        }catch (Exception e ){
            return e.getMessage() ;
        }
    }

    @PutMapping("/SaleTransactions/{id}")
    Object updateSaleTransaction(@RequestBody SaleTransaction newSaleTransaction, @PathVariable Long id) {
        try {
            return SaleTransactionService.updateSaleTransaction(newSaleTransaction,id);
        }catch (Exception e ){
            return e.getMessage() ;
        }
    }

    @DeleteMapping("/SaleTransactions/{id}")
    Object deleteSaleTransaction(@PathVariable Long id) {
        try {
            return SaleTransactionService.deleteSaleTransaction(id);
        }catch (Exception e ){
            return e.getMessage() ;
        }
    }
}
