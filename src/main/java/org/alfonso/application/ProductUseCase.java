package org.alfonso.application;

import org.alfonso.domain.product.Product;
import org.alfonso.domain.product.ProductId;

import java.util.Optional;

public class ProductUseCase
{
    private final ProductRepository productRepository;

    public ProductUseCase(ProductRepository productRepository)
    {
        this.productRepository = productRepository;
    }

    public void createProduct(Product product)
    {   productRepository.createProduct(product);}

    public Optional<Product> getProduct(ProductId productId)
    {   return productRepository.findByProductId(productId);}
}
