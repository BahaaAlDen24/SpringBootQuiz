package com.SpringBootQuiz.SpringBootQuiz.SalesOperations;

import com.SpringBootQuiz.SpringBootQuiz.Products.Product;
import com.SpringBootQuiz.SpringBootQuiz.Products.ProductService;
import com.SpringBootQuiz.SpringBootQuiz.SalesTransactions.SaleTransaction;
import com.SpringBootQuiz.SpringBootQuiz.SalesTransactions.SaleTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleOperationService {
    private final SaleOperationRepository repository;

    @Autowired
    private SaleTransactionService saleTransactionService;

    @Autowired
    private ProductService productService;

    SaleOperationService(SaleOperationRepository repository) {
        this.repository = repository;
    }

    public Object all() {
        try {
            return repository.findAll();
        }catch (Exception e ){
            return e.getMessage() ;
        }
    }

    public Object newSaleOperation(SaleOperation newSaleOperation) {
        try {
            for (SaleTransaction saleTransaction :newSaleOperation.getSaleTransactions()) {
                Product product = (Product) productService.getProductByID(saleTransaction.getProduct().getId()) ;
                saleTransaction.setUnitPrice(product.getPrice());
                saleTransaction.setTotalPrice(product.getPrice() * saleTransaction.getQuantity());
            }
            newSaleOperation.setTotalPrice(newSaleOperation.calculateTotalPrice());
            newSaleOperation = repository.save(newSaleOperation) ;
            for (SaleTransaction saleTransaction : newSaleOperation.getSaleTransactions())
            {
                saleTransaction.setSaleOperation(newSaleOperation);
                saleTransactionService.updateSaleTransaction(saleTransaction,saleTransaction.getId()) ;
            }
            return newSaleOperation ;
        }catch (Exception e ){
            return e.getMessage() ;
        }
    }

    public SaleOperation getSaleOperationByID(Long id) {
        try {
            return repository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Could not find SaleOperation : " + id .toString()));
        }catch (Exception e ){
            throw e;
        }
    }

    public Object updateSaleOperation(SaleOperation newSaleOperation,Long id) {
        try {

            for (SaleTransaction saleTransaction :newSaleOperation.getSaleTransactions()) {
                Product product = (Product) productService.getProductByID(saleTransaction.getProduct().getId()) ;
                saleTransaction.setUnitPrice(product.getPrice());
                saleTransaction.setTotalPrice(product.getPrice() * saleTransaction.getQuantity());
                saleTransaction.setSaleOperation(newSaleOperation);
                if (saleTransaction.getId()== null) {
                    saleTransactionService.newSaleTransaction(saleTransaction);
                }else if (saleTransactionService.existsById(saleTransaction.getId())){
                    saleTransactionService.updateSaleTransaction(saleTransaction, saleTransaction.getId());
                }
            }

            final  SaleOperation newSaleOperation2 = newSaleOperation ;
            newSaleOperation = repository.findById(id)
                    .map(SaleOperation -> {
                        SaleOperation.setClient(newSaleOperation2.getClient());
                        SaleOperation.setSeller(newSaleOperation2.getSeller());
                        SaleOperation.setTotalPrice(SaleOperation.calculateTotalPrice());
                        SaleOperation.setSaleTransactions(newSaleOperation2.getSaleTransactions());
                        return repository.save(SaleOperation);
                    })
                    .orElseThrow(() -> new RuntimeException("Could not find SaleOperation : " + id .toString()));

            return newSaleOperation ;

        }catch (Exception e ){
            return e.getMessage() ;
        }
    }

    public Object deleteSaleOperation(Long id) {
        try {
            repository.deleteById(id);
            return true ;
        }catch (Exception e){
            return e.getMessage() ;
        }
    }
}
