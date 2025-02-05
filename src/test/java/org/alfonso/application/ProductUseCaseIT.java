package org.alfonso.application;

import org.alfonso.domain.price.*;
import org.alfonso.domain.product.Product;
import org.alfonso.domain.product.ProductId;
import org.alfonso.domain.product.ProductName;
import org.alfonso.infraestructure.repositories.product.ProductEntityAdapter;
import org.alfonso.infraestructure.repositories.product.ProductRepositoryInMemory;
import org.alfonso.infraestructure.rest.product.PriceByProductIdBrandIdAndDateCommand;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

class ProductUseCaseIT {

    private ProductEntityAdapter adapter = new ProductEntityAdapter();
    private ProductRepository productRepository = new ProductRepositoryInMemory(adapter);
    private ProductUseCase productUseCase = new ProductUseCase(productRepository);
    private PriceUseCase priceUseCase = new PriceUseCase(productRepository);

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
    public void pim() {

        //Price1--------------------------------------------------------------------------------------------------------
        BrandId brandId1 = new BrandId(1);
        Currency currency1 = new Currency(CurrencyType.EUR);
        Date startDate1 = parseDate("14-06-2020 00:00:00");
        Date endDate1 = parseDate("31-12-2020 23:59:59");
        DateInRange dateInRange1 = new DateInRange(startDate1,endDate1);
        Money money1 = new Money(35.50f);
        Priority priority1 = new Priority(0);
        Price price1 = new Price(brandId1,currency1,dateInRange1,money1,priority1);
        //Price2--------------------------------------------------------------------------------------------------------
        BrandId brandId2 = new BrandId(1);
        Currency currency2 = new Currency(CurrencyType.EUR);
        Date startDate2 = parseDate("14-06-2020 15:00:00");
        Date endDate2 = parseDate("14-06-2020 18:30:00");
        DateInRange dateInRange2 = new DateInRange(startDate2,endDate2);
        Money money2 = new Money(25.45f);
        Priority priority2 = new Priority(1);
        Price price2 = new Price(brandId2,currency2,dateInRange2,money2,priority2);
        //Price3--------------------------------------------------------------------------------------------------------
        BrandId brandId3 = new BrandId(1);
        Currency currency3 = new Currency(CurrencyType.EUR);
        Date startDate3 = parseDate("15-06-2020 00:00:00");
        Date endDate3 = parseDate("15-06-2020 11:00:00");
        DateInRange dateInRange3 = new DateInRange(startDate3,endDate3);
        Money money3 = new Money(30.50f);
        Priority priority3 = new Priority(1);
        Price price3 = new Price(brandId3,currency3,dateInRange3,money3,priority3);
        //Price4--------------------------------------------------------------------------------------------------------
        BrandId brandId4 = new BrandId(1);
        Currency currency4 = new Currency(CurrencyType.EUR);
        Date startDate4 = parseDate("15-06-2020 16:00:00");
        Date endDate4 = parseDate("31-12-2020 23:59:59");
        DateInRange dateInRange4 = new DateInRange(startDate4,endDate4);
        Money money4 = new Money(38.95f);
        Priority priority4 = new Priority(1);
        Price price4 = new Price(brandId4,currency4,dateInRange4,money4,priority4);

        //Product1------------------------------------------------------------------------------------------------------
        ProductId productId1 = new ProductId(35455);
        ProductName productName1 = new ProductName("Camiseta");
        List<Price> priceList1 = Arrays.asList(price1,price2,price3,price4);
        Product product1 = new Product(productId1,productName1,priceList1);
        //Product2------------------------------------------------------------------------------------------------------
        ProductId productId2 = new ProductId(30000);
        ProductName productName2 = new ProductName("Pantalon");
        List<Price> priceList2 = Arrays.asList(price1,price2,price3,price4);
        Product product2 = new Product(productId2,productName2,priceList2);

        Optional<Price> expectedResult = Optional.ofNullable(price2);

        productUseCase.createProduct(product1);
        productUseCase.createProduct(product2);

        Date date = parseDate("14-06-2020 15:15:00");
        PriceByProductIdBrandIdAndDateCommand input = new PriceByProductIdBrandIdAndDateCommand(productId1,brandId1,date);

        System.out.println(priceUseCase.findPrice2(input));

        Optional<Price> result = priceUseCase.findPrice2(input);

        Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);

        System.out.println();

        System.out.println(price4);

    }


}