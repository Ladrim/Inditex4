
package org.alfonso.infraestructure.repositories.product;

import org.alfonso.application.ProductRepository;
import org.alfonso.domain.price.BrandId;
import org.alfonso.domain.price.Price;
import org.alfonso.domain.product.Product;
import org.alfonso.domain.product.ProductId;

import java.util.*;

public class ProductRepositoryInMemory implements ProductRepository
{
    private final ProductEntityAdapter productEntityAdapter;
    private final Map<ProductId ,ProductEntity> productEntityMap = new HashMap<>();

    public ProductRepositoryInMemory(ProductEntityAdapter productEntityAdapter)
    {
        this.productEntityAdapter = productEntityAdapter;
    }

    @Override
    public void createProduct(Product product)
    {
        ProductEntity productEntity = productEntityAdapter.toEntity(product);
        productEntityMap.put(product.getProductId(),productEntity);
    }

    @Override
    public Optional<Product> findByProductId(ProductId productId)
    {
        Optional<ProductEntity> productEntity = Optional.ofNullable(productEntityMap.get(productId));
        return productEntity.map(productEntityAdapter::toDomain);
    }

    @Override
    public List<Product>findAll()
    {
        return productEntityMap.values().stream()
            .map(productEntityAdapter::toDomain).toList();
    }

}
