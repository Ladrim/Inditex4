package org.alfonso.infraestructure.rest.product;

import java.util.List;

public class ProductResponse
{
    private final Integer productId;
    private final String productName;
    private final List<PriceResponse> priceResponseList;

    public ProductResponse(Integer productId, String productName, List<PriceResponse> priceResponseList)
    {
        this.productId = productId;
        this.productName = productName;
        this.priceResponseList = priceResponseList;
    }

    public Integer getProductId()
    {   return productId;}

    public String getProductName()
    {   return productName;}

    public List<PriceResponse> getPriceRessourceList()
    {   return priceResponseList;}
}
