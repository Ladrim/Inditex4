package org.alfonso.infraestructure.repositories.product;

import org.alfonso.domain.price.*;
import org.alfonso.domain.product.Product;
import org.alfonso.domain.product.ProductId;
import org.alfonso.domain.product.ProductName;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ProductEntityAdapter
{
    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    public Date parseDate(String date)
    {
        try
        {
            return df.parse(date);
        }
        catch (Exception e)
        {
            throw  new RuntimeException(e);
        }
    }

    public Product toDomain(ProductEntity productEntity, List<PriceEntity> priceEntities)
    {
        ProductId productId = new ProductId(productEntity.getProductId());
        ProductName productName = new ProductName(productEntity.getProductName());
        List<Price>priceList = priceEntities.stream().map(this::toDomain).toList();

        return new Product(productId,productName,priceList);
    }


    private Price toDomain(PriceEntity priceEntity)
    {
        BrandId brandId = new BrandId(priceEntity.getBrandId());
        Currency currency = new Currency(CurrencyType.valueOf(priceEntity.getCurrency()));
        Date startDate = parseDate(priceEntity.getStartDate());
        Date endDate = parseDate(priceEntity.getEndDate());
        DateInRange dateInRange = new DateInRange(startDate,endDate);
        Money money = new Money(priceEntity.getMoney());
        Priority priority = new Priority(priceEntity.getPriority());

        return new Price(brandId,currency,dateInRange,money,priority);
    }

    public ProductEntity toEntity(Product product)
    {
        Integer productId = product.getProductId().getValue();
        String productName = product.getProductName().getValue();
        List<Price>productList = product.getPriceList();
        List<PriceEntity>priceEntityList = productList.stream().map(this::toEntity).toList();

        return new ProductEntity(productId,productName);
    }

    public PriceEntity toEntity(Price price)
    {
        Integer brandId = price.getBrandId().getValue();
        String currency = price.getCurrency().getCurrencyType().getValue();
        String startDate = df.format(price.getDateInRange().getStartDate());
        String endDate = df.format(price.getDateInRange().getEndDate());
        Float money = price.getMoney().getValue();
        Integer priority = price.getPriority().getValue();

        return new PriceEntity(brandId,currency,startDate,endDate,money,priority);
    }

}
