package com.inventory.management.springboot.demo.controller;

import com.inventory.management.springboot.demo.entity.Product;
import com.inventory.management.springboot.demo.model.ProductModel;
import com.inventory.management.springboot.demo.service.ProductService;
import com.inventory.management.springboot.demo.utils.ApiResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody ProductModel model) {
        Product saved = productService.addProduct(model);
        return ResponseEntity.ok(new ApiResponse(true, "Product added", saved));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable Integer id, @RequestBody ProductModel model) {
        Optional<Product> updated = productService.updateProduct(id, model);
        return updated.map(product -> ResponseEntity.ok(new ApiResponse(true, "Product updated", product)))
                .orElseGet(() -> ResponseEntity.ok(new ApiResponse(false, "Product not found", null)));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Integer id) {
        boolean deleted = productService.deleteProduct(id);
        return ResponseEntity.ok(new ApiResponse(deleted, deleted ? "Product deleted" : "Product not found", null));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable Integer id) {
        Optional<Product> product = productService.getProductById(id);
        return product.map(p -> ResponseEntity.ok(new ApiResponse(true, "Product fetched", p)))
                .orElseGet(() -> ResponseEntity.ok(new ApiResponse(false, "Product not found", null)));
    }

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse> getAllProducts() {
        List<Product> list = productService.getAllProducts();
        return ResponseEntity.ok(new ApiResponse(true, "Products fetched", list));
    }
}
