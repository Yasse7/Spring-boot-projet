package com.raptor.ecommerceproject.services;

import com.raptor.ecommerceproject.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    public Product save(Product product);

    public Optional<Product> get(Long id);

    public void update(Product product);

    public void delete(Long id);

    public List<Product> findAll();
}
