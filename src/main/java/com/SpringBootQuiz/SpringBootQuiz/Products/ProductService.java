package com.SpringBootQuiz.SpringBootQuiz.Products;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;

    ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> all() {
        try {
            return repository.findAll();
        }catch (Exception e ){
            throw e ;
        }
    }

    public Product newProduct(Product newProduct) {
        try {
            return repository.save(newProduct);
        }catch (Exception e ){
            throw e ;
        }
    }

    public Product getProductByID(Long id) {
        try {
            return repository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Could not find Product : " + id .toString()));
        }catch (Exception e ){
            throw e ;
        }
    }

    public Object updateProduct(Product newProduct,Long id) {
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

    public Object deleteProduct(Long id) {
        try {
            repository.deleteById(id);
            return true ;
        }catch (Exception e){
            throw e ;
        }
    }
}
