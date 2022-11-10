package com.spring.ecommerce.service;

import com.spring.ecommerce.dto.AddProductDTO;
import com.spring.ecommerce.model.Category;
import com.spring.ecommerce.model.Product;
import com.spring.ecommerce.repository.CategoryRepository;
import com.spring.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
@Transactional
public class ProductService {

    private ProductRepository productRepository;

    private CategoryRepository categoryRepository;

    public ProductService(@Autowired ProductRepository productRepository, @Autowired CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }



    public Product addProduct(AddProductDTO productDTO, Long categoryId) {
        Product productToBeSaved = new Product();
        Category foundedCategory = categoryRepository.findById(categoryId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "the order you want to update was not found"));
        productToBeSaved.setCategory(foundedCategory);
        productToBeSaved.setDescription(productDTO.getDescription());
        productToBeSaved.setName(productDTO.getName());
        productToBeSaved.setPrice(productDTO.getPrice());
        return productRepository.save(productToBeSaved);
    }


    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getAllProductsByCategory(Long categoryId) {
        Category foundedCategory = categoryRepository.findById(categoryId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "the order you want to update was not found"));
        return productRepository.findAllByCategory(foundedCategory);
    }

    public Product updateProduct(Product product, Long productId) {
        Product foundedProduct = productRepository.findById(productId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "the product you want to update was not found"));
        foundedProduct.setName(product.getName());
        foundedProduct.setDescription(product.getDescription());
        foundedProduct.setPrice(product.getPrice());
        foundedProduct.setCategory(product.getCategory());
        return productRepository.save(foundedProduct);
    }



}

