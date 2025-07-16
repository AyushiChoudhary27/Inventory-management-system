package com.inventory.management.springboot.demo.service;

import com.inventory.management.springboot.demo.entity.Product;
import com.inventory.management.springboot.demo.model.ProductModel;
import com.inventory.management.springboot.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(ProductModel model) {
        Product product = new Product();
        product.setName(model.getName());
        product.setCategory(model.getCategory());
        product.setQuantity(model.getQuantity());
        product.setPrice(model.getPrice());

        return productRepository.save(product);
    }

    public Optional<Product> updateProduct(Integer id, ProductModel model) {
        return productRepository.findById(id).map(existing -> {
            existing.setName(model.getName());
            existing.setCategory(model.getCategory());
            existing.setQuantity(model.getQuantity());
            existing.setPrice(model.getPrice());
            return productRepository.save(existing);
        });
    }

    public boolean deleteProduct(Integer id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Integer id) {
        return productRepository.findById(id);
    }
}
