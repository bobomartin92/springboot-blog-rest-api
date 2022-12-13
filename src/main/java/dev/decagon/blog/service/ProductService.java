package dev.decagon.blog.service;

import dev.decagon.blog.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> searchProduct(String query);

    Product createProduct(Product product);
}
