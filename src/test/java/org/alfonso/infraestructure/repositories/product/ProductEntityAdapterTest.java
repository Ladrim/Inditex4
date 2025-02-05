package org.alfonso.infraestructure.repositories.product;

import org.alfonso.domain.price.*;
import org.alfonso.domain.product.Product;
import org.alfonso.domain.product.ProductId;
import org.alfonso.domain.product.ProductName;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductEntityAdapterTest
{
    ProductEntityAdapter undertest = new ProductEntityAdapter();
    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    public Date parseDate(String date)
    {
        try
        {
          return df.parse(date);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void entityToDomain()
    {
        //ProductEntity-------------------------------------------------------------------------------------------------
        Integer productIdEntity = 35455;
        String productNameEntity = "Camiseta";
        Integer brandIdEntity = 1;
        String currencyEntity = "EUR";
        String startDateEntity = "05-05-2020 22:10:10";
        String endDateEntity = "10-10-2020 22:10:10";
        Float moneyEntity = 40f;
        Integer priorityEntity = 1;

        PriceEntity priceEntity = new PriceEntity(brandIdEntity,currencyEntity,
                startDateEntity,endDateEntity,moneyEntity,priorityEntity);
        List<PriceEntity> priceEntityList = Arrays.asList(priceEntity);

        ProductEntity input = new ProductEntity(productIdEntity,productNameEntity,priceEntityList);
        Product result = undertest.toDomain(input);

        //Product-------------------------------------------------------------------------------------------------------
        ProductId productId = new ProductId(35455);
        ProductName productName = new ProductName("Camiseta");
        BrandId brandId = new BrandId(1);
        Currency currency = new Currency(CurrencyType.EUR);
        Date startDate = parseDate("05-05-2020 22:10:10");
        Date endDate = parseDate("10-10-2020 22:10:10");
        DateInRange dateInRange = new DateInRange(startDate,endDate);
        Money money = new Money(40f);
        Priority priority = new Priority(1);

        Price price = new Price(brandId,currency,dateInRange,money,priority);
        List<Price>priceList = Arrays.asList(price);
        Product expectedResult = new Product(productId,productName,priceList);


        //--------------------------------------------------------------------------------------------------------------

        Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);

    }
    @Test
    public void domainToEntity()
    {
        //Product-------------------------------------------------------------------------------------------------------
        ProductId productId = new ProductId(35455);
        ProductName productName = new ProductName("Camiseta");
        BrandId brandId = new BrandId(1);
        Currency currency = new Currency(CurrencyType.EUR);
        Date startDate = parseDate("05-05-2020 10:10:10");
        Date endDate = parseDate("10-10-2020 10:10:10");
        DateInRange dateInRange = new DateInRange(startDate,endDate);
        Money money = new Money(40f);
        Priority priority = new Priority(1);
        Price price = new Price(brandId,currency,dateInRange,money,priority);
        List<Price>priceList = Arrays.asList(price);

        Product input = new Product(productId,productName,priceList);
        ProductEntity result = undertest.toEntity(input);

        //ProductEntity-------------------------------------------------------------------------------------------------
        Integer productIdEntity = 35455;
        String productNameEntity = "Camiseta";
        Integer brandIdEntity = 1;
        String currencyEntity = "EUR";
        String startDateEntity  = "05-05-2020 10:10:10";
        String endDateEntity =  "10-10-2020 10:10:10";
        Float moneyEntity = 40f;
        Integer priorityEntity = 1;
        PriceEntity priceEntity = new PriceEntity(brandIdEntity,currencyEntity,startDateEntity,endDateEntity,
                moneyEntity,priorityEntity);

        List<PriceEntity>priceEntityList = Arrays.asList(priceEntity);

        ProductEntity expectedResult = new ProductEntity(productIdEntity,productNameEntity,priceEntityList);
        //--------------------------------------------------------------------------------------------------------------
        Assertions.assertThat(expectedResult).usingRecursiveComparison().isEqualTo(result);

    }
}