package com.inventory.management.springboot.demo.repository;

import com.inventory.management.springboot.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    	
}
