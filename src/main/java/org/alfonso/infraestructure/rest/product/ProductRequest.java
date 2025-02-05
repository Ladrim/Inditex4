package org.alfonso.infraestructure.rest.product;

import java.util.List;

public class ProductRequest
{
    private final Integer productId;
    private final String productName;
    private final List<PriceRequest> priceRequestList;

    public ProductRequest(Integer productId, String productName, List<PriceRequest> priceRequestList)
    {
        this.productId = productId;
        this.productName = productName;
        this.priceRequestList = priceRequestList;
    }

    public Integer getProductId()
    {   return productId;}

    public String getProductName()
    {   return productName;}

    public List<PriceRequest> getPriceRequestList()
    {   return priceRequestList;}
}

