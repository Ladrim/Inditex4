package org.alfonso.domain.product;

import org.alfonso.domain.price.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

class ProductTest
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

    @Nested
    class BraidIdIsInProductPriceList
    {
        @Test
        public void WhenBraidIsNotInProductPriceListItReturnsNull()
        {
            //First price-------------------------------------------------------------------------
            BrandId brandId1 = new BrandId(1);
            Currency currency1 = new Currency(CurrencyType.EUR);
            Date startDate1 = parseDate("14-06-2020 00:00:00");
            Date endDate1 = parseDate("31-12-2020 23:59:59");
            DateInRange dateInRange1 = new DateInRange(startDate1,endDate1);
            Money money1 = new Money(35.50f);
            Priority priority1 = new Priority(0);
            Price price1 = new Price(brandId1,currency1,dateInRange1,money1,priority1);

            //Second price---------------------------------------------------------------------------
            BrandId brandId2 = new BrandId(1);
            Currency currency2 = new Currency(CurrencyType.EUR);
            Date startDate2 = parseDate("14-06-2020 15:00:00");
            Date endDate2 = parseDate("14-06-2020 18:30:00");
            DateInRange dateInRange2 = new DateInRange(startDate2,endDate2);
            Money money2 = new Money(25.45f);
            Priority priority2= new Priority(1);
            Price price2 = new Price(brandId2,currency2,dateInRange2,money2,priority2);

            //----------------------------------------------------------------------------------------

            //Product---------------------------------------------------------------------------------
            ProductId productId1 = new ProductId(35455);
            ProductName productName1 = new ProductName("First product");
            List<Price>priceList1 = Arrays.asList(price1,price2);

            Product underTest = new Product(productId1, productName1, priceList1);
            //-----------------------------------------------------------------------------------------

            Date date = parseDate("14-06-2020 15:30:00");
            Price price = underTest.findBy1(new BrandId(2),date).orElse(null);

            Assertions.assertThat(price).isNull();
        }

        @Test
        public void WhenBraidIsInProductPriceListItDoesNotReturnNull()
        {
            //First price-------------------------------------------------------------------------
            BrandId brandId1 = new BrandId(1);
            Currency currency1 = new Currency(CurrencyType.EUR);
            Date startDate1 = parseDate("14-06-2020 00:00:00");
            Date endDate1 = parseDate("31-12-2020 23:59:59");
            DateInRange dateInRange1 = new DateInRange(startDate1,endDate1);
            Money money1 = new Money(35.50f);
            Priority priority1 = new Priority(0);
            Price price1 = new Price(brandId1,currency1,dateInRange1,money1,priority1);

            //Second price---------------------------------------------------------------------------
            BrandId brandId2 = new BrandId(1);
            Currency currency2 = new Currency(CurrencyType.EUR);
            Date startDate2 = parseDate("14-06-2020 15:00:00");
            Date endDate2 = parseDate("14-06-2020 18:30:00");
            DateInRange dateInRange2 = new DateInRange(startDate2,endDate2);
            Money money2 = new Money(25.45f);
            Priority priority2= new Priority(1);
            Price price2 = new Price(brandId2,currency2,dateInRange2,money2,priority2);

            //Product---------------------------------------------------------------------------------
                ProductId productId1 = new ProductId(35455);
            ProductName productName1 = new ProductName("First product");
            List<Price>priceList1 = Arrays.asList(price1,price2);

            Product underTest = new Product(productId1, productName1, priceList1);
            //-----------------------------------------------------------------------------------------

            Date date = parseDate("14-06-2020 15:30:00");
            Price price = underTest.findBy1(new BrandId(1),date).orElse(null);

            Assertions.assertThat(price).isNotNull();
        }
        @Nested
        class DateIsInInRange
        {
            @Test
            public void whenDateIsNotInRangeItReturnsNull()
            {
                //First price-------------------------------------------------------------------------
                BrandId brandId1 = new BrandId(1);
                Currency currency1 = new Currency(CurrencyType.EUR);
                Date startDate1 = parseDate("14-06-2020 00:00:00");
                Date endDate1 = parseDate("31-12-2020 23:59:59");
                DateInRange dateInRange1 = new DateInRange(startDate1,endDate1);
                Money money1 = new Money(35.50f);
                Priority priority1 = new Priority(0);
                Price price1 = new Price(brandId1,currency1,dateInRange1,money1,priority1);

                //Second price---------------------------------------------------------------------------
                BrandId brandId2 = new BrandId(1);
                Currency currency2 = new Currency(CurrencyType.EUR);
                Date startDate2 = parseDate("14-06-2020 15:00:00");
                Date endDate2 = parseDate("14-06-2020 18:30:00");
                DateInRange dateInRange2 = new DateInRange(startDate2,endDate2);
                Money money2 = new Money(25.45f);
                Priority priority2= new Priority(1);
                Price price2 = new Price(brandId2,currency2,dateInRange2,money2,priority2);

                //Product---------------------------------------------------------------------------------
                ProductId productId1 = new ProductId(35455);
                ProductName productName1 = new ProductName("First product");
                List<Price>priceList1 = Arrays.asList(price1,price2);

                Product underTest = new Product(productId1, productName1, priceList1);
                //-----------------------------------------------------------------------------------------

                Date date = parseDate("14-06-2025 15:30:00");
                Price price = underTest.findBy1(new BrandId(1),date).orElse(null);

                Assertions.assertThat(price).isNull();
            }
            @Test
            public void whenDateIsInRangeExpectedPriceIsEqualToPrice()
            {
                //First price-------------------------------------------------------------------------
                BrandId brandId1 = new BrandId(1);
                Currency currency1 = new Currency(CurrencyType.EUR);
                Date startDate1 = parseDate("14-06-2020 00:00:00");
                Date endDate1 = parseDate("31-12-2020 23:59:59");
                DateInRange dateInRange1 = new DateInRange(startDate1,endDate1);
                Money money1 = new Money(35.50f);
                Priority priority1 = new Priority(0);
                Price price1 = new Price(brandId1,currency1,dateInRange1,money1,priority1);

                //Second price---------------------------------------------------------------------------
                BrandId brandId2 = new BrandId(1);
                Currency currency2 = new Currency(CurrencyType.EUR);
                Date startDate2 = parseDate("14-06-2020 15:00:00");
                Date endDate2 = parseDate("14-06-2020 18:30:00");
                DateInRange dateInRange2 = new DateInRange(startDate2,endDate2);
                Money money2 = new Money(25.45f);
                Priority priority2= new Priority(1);
                Price price2 = new Price(brandId2,currency2,dateInRange2,money2,priority2);

                //Product---------------------------------------------------------------------------------
                ProductId productId1 = new ProductId(35455);
                ProductName productName1 = new ProductName("First product");
                List<Price>priceList1 = Arrays.asList(price1,price2);

                Product underTest = new Product(productId1, productName1, priceList1);
                //-----------------------------------------------------------------------------------------

                Date date = parseDate("14-06-2020 15:30:00");
                Price expectedPrice = underTest.findBy1(new BrandId(1),date).orElse(null);

                Assertions.assertThat(expectedPrice).usingRecursiveComparison().isEqualTo(price2);
            }

        }
    }






    /*//Third price---------------------------------------------------------------------------
    BrandId brandId3 = new BrandId(1);
    Currency currency3 = new Currency(CurrencyType.EUR);
    Date startDate3 = parseDate("15-06-2020 00:00:00");
    Date endDate3 = parseDate("15-06-2020 11:00:00");
    DateInRange dateInRange3 = new DateInRange(startDate3,endDate3);
    Money money3 = new Money(30.50f);
    Priority priority3= new Priority(1);
    Price price3 = new Price(brandId3,currency3,dateInRange3,money3,priority3);

    //Fourth price---------------------------------------------------------------------------
    BrandId brandId4 = new BrandId(1);
    Currency currency4 = new Currency(CurrencyType.EUR);
    Date startDate4 = parseDate("15-06-2020 16:00:00");
    Date endDate4 = parseDate("31-12-2020 23:59:59");
    DateInRange dateInRange4 = new DateInRange(startDate4,endDate4);
    Money money4 = new Money(38.95f);
    Priority priority4= new Priority(1);
    Price price4 = new Price(brandId4,currency4,dateInRange4,money4,priority4);

    //Fifth price---------------------------------------------------------------------------
    BrandId brandId5 = new BrandId(2);
    Currency currency5 = new Currency(CurrencyType.EUR);
    Date startDate5 = parseDate("15-06-2020 16:00:00");
    Date endDate5 = parseDate("31-12-2020 23:59:59");
    DateInRange dateInRange5 = new DateInRange(startDate5,endDate5);
    Money money5 = new Money(38.95f);
    Priority priority5= new Priority(1);
    Price price5 = new Price(brandId5,currency5,dateInRange5,money5,priority5);*/
}