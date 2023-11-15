package com.clix.server.controller;

import com.clix.server.model.Product;
import com.clix.server.repository.ProductoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    private ProductoRepo productoRepo;

    @RequestMapping
    public ResponseEntity<List<Product>> getProducts(){
        return ResponseEntity.ok((List<Product>) productoRepo.findAll());
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        return ResponseEntity.ok(productoRepo.save(product));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        productoRepo.deleteById(id);
        return ResponseEntity.ok(null);
    }



}
