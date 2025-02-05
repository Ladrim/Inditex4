package org.alfonso.infraestructure.repositories.product;

import org.alfonso.domain.price.*;
import org.alfonso.domain.price.Currency;
import org.alfonso.domain.product.Product;
import org.alfonso.domain.product.ProductId;
import org.alfonso.domain.product.ProductName;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.text.SimpleDateFormat;
import java.util.*;

class ProductRepositoryInMemoryTest
{
    ProductEntityAdapter adapter = Mockito.mock(ProductEntityAdapter.class);
    ProductRepositoryInMemory underTest = new ProductRepositoryInMemory(adapter);

    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");


    //Product1----------------------------------------------------------------------------------------------------------

    ProductId productId1 = new ProductId(35455);
    ProductName productName1 = new ProductName("Camiseta");
    BrandId brandId1 = new BrandId(1);
    Currency currency1 = new Currency(CurrencyType.EUR);
    Date startDate1 = parseDate("05-05-2020 10:10:10");
    Date endDate1 = parseDate("10-10-2020 10:10:10");
    DateInRange dateInRange1 = new DateInRange(startDate1, endDate1);
    Money money1 = new Money(45f);
    Priority priority1 = new Priority(1);
    Price price1 = new Price(brandId1, currency1, dateInRange1, money1, priority1);
    List<Price> priceList1 = Arrays.asList(price1);
    Product product1 = new Product(productId1, productName1, priceList1);

    //Product2----------------------------------------------------------------------------------------------------------

    ProductId productId2 = new ProductId(35454);
    ProductName productName2 = new ProductName("Camiseta");
    BrandId brandId2 = new BrandId(1);
    Currency currency2 = new Currency(CurrencyType.EUR);
    Date startDate2 = parseDate("05-05-2020 10:10:10");
    Date endDate2 = parseDate("10-10-2020 10:10:10");
    DateInRange dateInRange2 = new DateInRange(startDate2, endDate2);
    Money money2 = new Money(45f);
    Priority priority2 = new Priority(1);
    Price price2 = new Price(brandId2, currency2, dateInRange2, money2, priority2);
    List<Price>priceList2 = Arrays.asList(price2);
    Product product2 = new Product(productId2, productName2, priceList2);


    //ProductEntity1----------------------------------------------------------------------------------------------------
    Integer productIdEntity1 = 35455;
    String productNameEntity1 = "Camiseta";
    Integer brandIdEntity1 = 1;
    String currencyEntity1 = "EUR";
    String startDateEntity1 = "05-05-2020 10:10:10";
    String endDateEntity1 = "10-10-2020 10:10:10";
    Float moneyEntity1 = 40f;
    Integer priorityEntity1 = 1;
    PriceEntity priceEntity1 = new PriceEntity(brandIdEntity1, currencyEntity1, startDateEntity1, endDateEntity1, moneyEntity1, priorityEntity1);
    List<PriceEntity> priceEntityList1 = Arrays.asList(priceEntity1);
    ProductEntity productEntity1 = new ProductEntity(productIdEntity1, productNameEntity1, priceEntityList1);


    //ProductEntity2----------------------------------------------------------------------------------------------------
    Integer productIdEntity2 = 35454;
    String productNameEntity2 = "Camiseta";
    Integer brandIdEntity2 = 1;
    String currencyEntity2 = "EUR";
    String startDateEntity2 = "05-05-2020 10:10:10";
    String endDateEntity2 = "10-10-2020 10:10:10";
    Float moneyEntity2 = 40f;
    Integer priorityEntity2 = 1;
    PriceEntity priceEntity2 = new PriceEntity(brandIdEntity2, currencyEntity2, startDateEntity2, endDateEntity2, moneyEntity2, priorityEntity2);
    List<PriceEntity> priceEntityList2 = Arrays.asList(priceEntity2);
    ProductEntity productEntity2 = new ProductEntity(productIdEntity2, productNameEntity2, priceEntityList2);

    @Nested
    class CreateAndFindProduct
    {
        @Nested
        class WhenTheRepositoryIsEmpty
        {
            @Test
            public void findByIdShouldNotFindIt()
            {
                var result = underTest.findByProductId(productId1);

                Assertions.assertThat(result).isEmpty();
            }

            @Test
            public void findByFindAllShouldReturnAnEmptyList()
            {
                var result = underTest.findAll();

                Assertions.assertThat(result).isEmpty();
            }
        }


        @Nested
        class WhenWeAddOneProductToTheRepository
        {

            @Test
            public void findByIdShouldFindIt1()
            {
                Mockito.when(adapter.toEntity(product1)).thenReturn(productEntity1);
                Mockito.when(adapter.toDomain(productEntity1)).thenReturn(product1);

                underTest.createProduct(product1);

                Optional<Product> result = underTest.findByProductId(productId1);
                Optional<Product> expectedResult = Optional.of(product1);

                Assertions.assertThat(result).isNotEmpty();
                Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
            }
            @Test
            public void findAllShouldFindIt()
            {
                Mockito.when(adapter.toEntity(product1)).thenReturn(productEntity1);
                Mockito.when(adapter.toDomain(productEntity1)).thenReturn(product1);

                underTest.createProduct(product1);

                List<Product> result = underTest.findAll();

                Assertions.assertThat(result).hasSize(1);
                Assertions.assertThat(result.getFirst()).usingRecursiveComparison().isEqualTo(product1);
            }
        }

        @Nested
        class WhenWeAddTwoProductsToTheRepository
        {
            @Test
            public void findByIdShouldFindIt1()
            {
                Mockito.when(adapter.toEntity(product1)).thenReturn(productEntity1);
                Mockito.when(adapter.toDomain(productEntity1)).thenReturn(product1);
                Mockito.when(adapter.toEntity(product2)).thenReturn(productEntity2);
                Mockito.when(adapter.toDomain(productEntity2)).thenReturn(product2);


                underTest.createProduct(product1);
                underTest.createProduct(product2);

                Optional<Product> result = underTest.findByProductId(productId1);
                Optional<Product> expectedResult = Optional.of(product1);

                Assertions.assertThat(result).isNotEmpty();
                Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
            }
            @Test
            public void findAllShouldFindThem()
            {
                Mockito.when(adapter.toEntity(product1)).thenReturn(productEntity1);
                Mockito.when(adapter.toDomain(productEntity1)).thenReturn(product1);
                Mockito.when(adapter.toEntity(product2)).thenReturn(productEntity2);
                Mockito.when(adapter.toDomain(productEntity2)).thenReturn(product2);

                underTest.createProduct(product1);
                underTest.createProduct(product2);

                List<Product> result = underTest.findAll();
                List<Product> expectedResult = Arrays.asList(product1,product2);

                Assertions.assertThat(result).hasSize(2);
                Assertions.assertThat(result.stream().anyMatch( it -> it.equals(product1))).isTrue();
                Assertions.assertThat(result.stream().anyMatch( it -> it.equals(product2))).isTrue();

                Assertions.assertThat(result).containsAll(expectedResult);

            }
        }
    }

    private Date parseDate(String date)
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
}