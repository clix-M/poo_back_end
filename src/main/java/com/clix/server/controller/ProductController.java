package com.clix.server.controller;

import com.clix.server.model.Product;
import com.clix.server.repository.ProductoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    private ProductoRepo productoRepo;

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.ok((List<Product>) productoRepo.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        Optional<Product> productOpt = productoRepo.findById(id);
        if (productOpt.isPresent()) {
            return ResponseEntity.ok(productOpt.get());
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productoRepo.save(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productoRepo.deleteById(id);
        return ResponseEntity.ok(null);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Optional<Product> productOpt = productoRepo.findById(id);
        if (productOpt.isPresent()) {
            Product productFound = productOpt.get();
            productFound.setName(product.getName());
            productFound.setPrice(product.getPrice());
            return ResponseEntity.ok(productoRepo.save(productFound));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    

}
