package org.alfonso.infraestructure.rest.product;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.alfonso.domain.price.BrandId;
import org.alfonso.domain.product.ProductId;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

public class PriceByProductIdBrandIdAndDateRequest
{

    private final Integer productId;
    private final Integer brandId;
    private final @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss") Date date;

    public PriceByProductIdBrandIdAndDateRequest(Integer productId, Integer brandId, Date date)
    {
        this.productId = productId;
        this.brandId = brandId;
        this.date = date;
    }

    public Integer getProductId()
    {   return productId;}

    public Integer getBrandId()
    {   return brandId;}

    public Date getDate()
    {   return date;}
}

