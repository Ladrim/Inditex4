package org.alfonso.infraestructure.repositories.product;

import java.util.List;

public class ProductEntity
{
    private final Integer productId;
    private final String productName;

    public ProductEntity(Integer productId, String productName)
    {
        this.productId = productId;
        this.productName = productName;
    }

    public Integer getProductId()
    {   return productId;}

    public String getProductName()
    {   return productName;}
}
