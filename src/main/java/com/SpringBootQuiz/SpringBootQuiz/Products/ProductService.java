package com.SpringBootQuiz.SpringBootQuiz.Products;

import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository repository;

    ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    Object all() {
        try {
            return repository.findAll();
        }catch (Exception e ){
            return e.getMessage() ;
        }
    }

    Object newProduct(Product newProduct) {
        try {
            return repository.save(newProduct);
        }catch (Exception e ){
            return e.getMessage() ;
        }
    }

    Object getProductByID(Long id) {
        try {
            return repository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Could not find Product : " + id .toString()));
        }catch (Exception e ){
            return e.getMessage() ;
        }
    }

    Object updateProduct(Product newProduct,Long id) {
        try {
            return repository.findById(id)
                    .map(Product -> {
                        Product.setName(newProduct.getName());
                        Product.setDescription(newProduct.getDescription());
                        Product.setCategory(newProduct.getCategory());
                        Product.setPrice(newProduct.getPrice());
                        Product.setQuantity(newProduct.getQuantity());
                        return repository.save(Product);
                    })
                    .orElseThrow(() -> new RuntimeException("Could not find Product : " + id .toString()));
        }catch (Exception e ){
            return e.getMessage() ;
        }
    }

    Object deleteProduct(Long id) {
        try {
            repository.deleteById(id);
            return true ;
        }catch (Exception e){
            return e.getMessage() ;
        }
    }
}
