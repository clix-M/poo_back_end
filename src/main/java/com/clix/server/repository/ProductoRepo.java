package com.clix.server.repository;

import com.clix.server.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductoRepo extends CrudRepository<Product,Long> {
}
