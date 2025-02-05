package org.alfonso.infraestructure.rest.product;

import org.alfonso.domain.price.BrandId;
import org.alfonso.domain.product.ProductId;

import java.util.Date;

public class PriceByProductIdBrandIdAndDateCommand
{
    private final ProductId productId;
    private final BrandId brandId;
    private final Date date;

    public PriceByProductIdBrandIdAndDateCommand(ProductId productId, BrandId brandId, Date date)
    {
        this.productId = productId;
        this.brandId = brandId;
        this.date = date;
    }

    public ProductId getProductId()
    {   return productId;}

    public BrandId getBrandId()
    {   return brandId;}

    public Date getDate()
    {   return date;}
}
