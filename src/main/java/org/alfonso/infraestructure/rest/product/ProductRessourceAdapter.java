package org.alfonso.infraestructure.rest.product;

import org.alfonso.domain.price.*;
import org.alfonso.domain.product.Product;
import org.alfonso.domain.product.ProductId;
import org.alfonso.domain.product.ProductName;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ProductRessourceAdapter
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

    public Product ProductRequestToDomain(ProductRequest request)
    {
        ProductId productId = new ProductId(request.getProductId());
        ProductName productName = new ProductName(request.getProductName());
        List<Price>priceList = request.getPriceRequestList().stream()
                .map(this::PriceRequestToDomain)
                .toList();

        return new Product(productId, productName, priceList );
    }

    public ProductResponse ProductToRessource(Product product)
    {
        Integer productIdResponse = product.getProductId().getValue();
        String productNameResponse = product.getProductName().getValue();
        List<PriceResponse>priceResponseList = product.getPriceList().stream().map(this::PriceToRessource).toList();

        return new ProductResponse(productIdResponse,productNameResponse,priceResponseList);
    }

    public PriceByProductIdBrandIdAndDateCommand PriceByProductIdBrandIdAndDateRequestToDomain(PriceByProductIdBrandIdAndDateRequest request)
    {
        ProductId productId = new ProductId(request.getProductId());
        BrandId brandId = new BrandId(request.getBrandId());
        Date date = request.getDate();

        return new PriceByProductIdBrandIdAndDateCommand(productId,brandId,date);
    }

    public Price PriceRequestToDomain(PriceRequest request)
    {
        BrandId brandId = new BrandId(request.getBrandId());
        Currency currency = new Currency(CurrencyType.valueOf(request.getCurrency()));
        Date startDate = parseDate(request.getStartDate());
        Date endDate = parseDate(request.getEndDate());
        DateInRange dateInRange = new DateInRange(startDate,endDate);
        Money money = new Money(request.getMoney());
        Priority priority = new Priority(request.getPriority());
        return  new Price(brandId,currency,dateInRange,money,priority);
    }

    public PriceResponse PriceToRessource(Price price)
    {
        Integer brandIdResponse = price.getBrandId().getValue();
        String currencyResponse = price.getCurrency().getCurrencyType().getValue();
        String startDateResponse = df.format(price.getDateInRange().getStartDate());
        String endDateResponse = df.format(price.getDateInRange().getEndDate());
        Float moneyResponse = price.getMoney().getValue();
        Integer priorityRenspose = price.getPriority().getValue();

        return new PriceResponse(brandIdResponse,currencyResponse,startDateResponse,endDateResponse,moneyResponse,priorityRenspose);
    }
}



