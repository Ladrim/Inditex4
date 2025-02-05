package org.alfonso.application;

import org.alfonso.domain.price.BrandId;
import org.alfonso.domain.price.Price;
import org.alfonso.domain.product.Product;
import org.alfonso.domain.product.ProductId;
import org.alfonso.infraestructure.repositories.product.ProductEntity;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ProductRepository
{
    void createProduct(Product product);

    Optional<Product> findByProductId(ProductId productId);

    List<Product> findAll();

}