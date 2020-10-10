package com.SpringBootQuiz.SpringBootQuiz.SaleTransaction;

import org.springframework.stereotype.Service;

@Service
public class SaleTransactionService {
    private final SaleTransactionRepository repository;

    SaleTransactionService(SaleTransactionRepository repository) {
        this.repository = repository;
    }

    public Object all() {
        try {
            return repository.findAll();
        }catch (Exception e ){
            return e.getMessage() ;
        }
    }

    public Object newSaleTransaction(SaleTransaction newSaleTransaction) {
        try {
            return repository.save(newSaleTransaction);
        }catch (Exception e ){
            return e.getMessage() ;
        }
    }

    public Object getSaleTransactionByID(Long id) {
        try {
            return repository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Could not find SaleTransaction : " + id .toString()));
        }catch (Exception e ){
            return e.getMessage() ;
        }
    }

    public Object updateSaleTransaction(SaleTransaction newSaleTransaction, Long id) {
        try {
            return repository.findById(id)
                    .map(SaleTransaction -> {
                        SaleTransaction.setProduct(newSaleTransaction.getProduct());
                        SaleTransaction.setSaleOperation(newSaleTransaction.getSaleOperation());
                        SaleTransaction.setQuantity(newSaleTransaction.getQuantity());
                        SaleTransaction.setUnitPrice(newSaleTransaction.getUnitPrice());
                        SaleTransaction.setTotalPrice(newSaleTransaction.getTotalPrice());
                        return repository.save(SaleTransaction);
                    })
                    .orElseThrow(() -> new RuntimeException("Could not find SaleTransaction : " + id .toString()));
        }catch (Exception e ){
            return e.getMessage() ;
        }
    }

//    public Object saveOrUpdateSaleTransaction(SaleTransaction newSaleTransaction) {
//        try {
//            if (repository.findById(newSaleTransaction.getId()) != null){
//                SaleTransaction saleTransaction = new SaleTransaction();
//                saleTransaction.setProduct(newSaleTransaction.getProduct());
//                saleTransaction.setSaleOperation(newSaleTransaction.getSaleOperation());
//                saleTransaction.setQuantity(newSaleTransaction.getQuantity());
//                SaleTransaction.setUnitPrice(newSaleTransaction.getUnitPrice());
//                SaleTransaction.setTotalPrice(newSaleTransaction.getTotalPrice());
//                return repository.save(SaleTransaction);
//            }else{
//
//            }
//            return repository.findById(newSaleTransaction.getId())
//                    .map(SaleTransaction -> {
//                        SaleTransaction.setProduct(newSaleTransaction.getProduct());
//                        SaleTransaction.setSaleOperation(newSaleTransaction.getSaleOperation());
//                        SaleTransaction.setQuantity(newSaleTransaction.getQuantity());
//                        SaleTransaction.setUnitPrice(newSaleTransaction.getUnitPrice());
//                        SaleTransaction.setTotalPrice(newSaleTransaction.getTotalPrice());
//                        return repository.save(SaleTransaction);
//                    })
//                    .orElse(() ->  repository.save(newSaleTransaction););
//        }catch (Exception e ){
//            return e.getMessage() ;
//        }
//    }
//
    public boolean existsById(Long id) {
           return repository.existsById(id);
    }
    public Object deleteSaleTransaction(Long id) {
        try {
            repository.deleteById(id);
            return true ;
        }catch (Exception e){
            return e.getMessage() ;
        }
    }
}
