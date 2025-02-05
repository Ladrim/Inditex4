/*
package org.alfonso.infraestructure;

import org.alfonso.domain.price.*;
import org.alfonso.domain.product.Product;
import org.alfonso.domain.product.ProductId;
import org.alfonso.domain.product.ProductName;
import org.alfonso.infraestructure.rest.product.PriceResponse;
import org.alfonso.infraestructure.rest.product.CreateProductResponse;
import org.alfonso.infraestructure.rest.product.ProductRessourceAdapter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

class ProductRessourceAdapterTest
{
    ProductRessourceAdapter underTest = new ProductRessourceAdapter();
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

    @Test
    public void createProductRequestProductRequestToDomain()
    {
        //ProductRequest------------------------------------------------------------------------------------------------
        Integer productIdRequest = 354555;
        String productNameRequest = "product1";
        Integer brandIdRequest = 1;
        String currencyRequest = "EUR";
        String startDateRequest = "05-05-2020 10:10:10";
        String endDateRequest = "10-10-2020 10:10:10";
        Float moneyRequest = 40f;
        Integer priorityRequest = 1;
        PriceResponse priceResponse = new PriceResponse(brandIdRequest,currencyRequest,startDateRequest,endDateRequest,
            moneyRequest,priorityRequest);
        List<PriceResponse> priceResponseList = List.of(priceResponse);
        CreateProductResponse input = new CreateProductResponse(productIdRequest,productNameRequest, priceResponseList);

        var result = underTest.toDomain(input);

        //Product-------------------------------------------------------------------------------------------------------
        ProductId productId = new ProductId(354555);
        ProductName productName = new ProductName("product1");
        BrandId brandId = new BrandId(1);
        Currency currency = new Currency(CurrencyType.EUR);
        Date startDate = parseDate("05-05-2020 10:10:10");
        Date endDate = parseDate("10-10-2020 10:10:10");
        DateInRange dateInRange = new DateInRange(startDate,endDate);
        Money money = new Money(40f);
        Priority priority = new Priority(1);
        Price price = new Price(brandId,currency,dateInRange,money,priority);
        List<Price>priceList = List.of(price);
        Product expectedResult = new Product(productId,productName,priceList);

        //--------------------------------------------------------------------------------------------------------------

        Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
    }
    @Test
    public void domainToCreateProductRequest()
    {
        //Product-------------------------------------------------------------------------------------------------------
        ProductId productId = new ProductId(354555);
        ProductName productName = new ProductName("product1");
        BrandId brandId = new BrandId(1);
        Currency currency = new Currency(CurrencyType.EUR);
        Date startDate = parseDate("05-05-2020 10:10:10");
        Date endDate = parseDate("10-10-2020 10:10:10");
        DateInRange dateInRange = new DateInRange(startDate,endDate);
        Money money = new Money(40f);
        Priority priority = new Priority(1);
        Price price = new Price(brandId,currency,dateInRange,money,priority);
        List<Price>priceList = List.of(price);
        Product input = new Product(productId,productName,priceList);

        var result = underTest.ProductToRessource(input);


        //ProductRequest------------------------------------------------------------------------------------------------
        Integer productIdRequest = 354555;
        String productNameRequest = "product1";
        Integer brandIdRequest = 1;
        String currencyRequest = "EUR";
        String startDateRequest = "05-05-2020 10:10:10";
        String endDateRequest = "10-10-2020 10:10:10";
        Float moneyRequest = 40f;
        Integer priorityRequest = 1;
        PriceResponse priceResponse = new PriceResponse(brandIdRequest,currencyRequest,startDateRequest,endDateRequest,
                moneyRequest,priorityRequest);
        List<PriceResponse> priceResponseList = List.of(priceResponse);
        CreateProductResponse expectedResult = new CreateProductResponse(productIdRequest,productNameRequest, priceResponseList);
        //--------------------------------------------------------------------------------------------------------------

        Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);




    }
}*/
