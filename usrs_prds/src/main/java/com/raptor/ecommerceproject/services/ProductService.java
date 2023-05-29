package com.raptor.ecommerceproject.services;

import com.raptor.ecommerceproject.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    //Método para implementar y guardar
    public Product save(Product product);
    //Método para implementar y buscar un producto si es que existe o no en la base de datos.
    public Optional<Product> get(Long id);
    //Método para implementar y actualizar un producto
    public void update(Product product);
    //Método a implementar y eliminar un producto
    public void delete(Long id);
    //Método para listar todo
    public List<Product> findAll();
}
