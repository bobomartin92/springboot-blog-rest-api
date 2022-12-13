package dev.decagon.blog.service.impl;

import dev.decagon.blog.entity.Product;
import dev.decagon.blog.repository.ProductRepository;
import dev.decagon.blog.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> searchProduct(String query) {
        return productRepository.searchProducts(query);
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
}
