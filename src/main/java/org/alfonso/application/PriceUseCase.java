package org.alfonso.application;

import org.alfonso.domain.price.BrandId;
import org.alfonso.domain.price.Price;
import org.alfonso.domain.product.Product;
import org.alfonso.domain.product.ProductId;
import org.alfonso.infraestructure.rest.product.PriceByProductIdBrandIdAndDateCommand;

import java.util.Date;
import java.util.Optional;

public class PriceUseCase
{
    private final ProductRepository productRepository;

    public PriceUseCase(ProductRepository productRepository)
    {
        this.productRepository = productRepository;
    }

    public Optional<Price> findPrice1(ProductId productId, BrandId brandId, Date date)
    {
       Optional<Product> optionalProduct = productRepository.findByProductId(productId);
       return optionalProduct.flatMap(it -> it.findBy1(brandId,date));
    }

    public Optional<Price> findPrice2(PriceByProductIdBrandIdAndDateCommand priceByProductIdBrandIdAndDateCommand)
    {

        Optional<Product> optionalProduct = productRepository.findByProductId(priceByProductIdBrandIdAndDateCommand.getProductId());
        return  optionalProduct.flatMap(it->it.findBy2(priceByProductIdBrandIdAndDateCommand));
    }
}
