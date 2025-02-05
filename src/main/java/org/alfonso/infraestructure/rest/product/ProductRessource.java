package org.alfonso.infraestructure.rest.product;

import org.alfonso.application.PriceUseCase;
import org.alfonso.application.ProductUseCase;
import org.alfonso.domain.price.BrandId;
import org.alfonso.domain.price.Price;
import org.alfonso.domain.product.Product;
import org.alfonso.domain.product.ProductId;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController()
public class ProductRessource
{
    public final ProductRessourceAdapter adapter;
    public final PriceUseCase priceUseCase;
    public final ProductUseCase productUseCase;

    public ProductRessource(ProductRessourceAdapter adapter, PriceUseCase priceUseCase, ProductUseCase productUseCase)
    {
        this.adapter = adapter;
        this.priceUseCase = priceUseCase;
        this.productUseCase = productUseCase;
    }

    // MAIN:
    //------------------------------------------------------------------------------------------------------------------

    @PostMapping("product/")
    public void createProduct(@RequestBody ProductRequest productRequest)
    {
        Product product = adapter.ProductRequestToDomain(productRequest);
        productUseCase.createProduct(product);
    }

    @GetMapping("product/{productId}")
    public ProductResponse findProduct(@PathVariable Integer productId)
    {
        return productUseCase.getProduct(new ProductId(productId))
            .map(adapter::ProductToRessource).orElse(null);
    }

    @GetMapping("price/")
    public PriceResponse findPrice(
            @RequestParam(value = "ProductId",required = false) ProductId productId,
            @RequestParam(value = "BrandId",required = false) BrandId brandId,
            @RequestParam(value = "Date",required = false) @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss") Date date)
    {
        Optional<Price> optionalPrice = priceUseCase.findPrice1(productId,brandId,date);
        Optional<PriceResponse> optionalPriceResponse = optionalPrice.map(adapter::PriceToRessource);
        return optionalPriceResponse.orElse(null);
    }

    @PostMapping("price2/")
    public PriceResponse findPrice2(@RequestBody PriceByProductIdBrandIdAndDateRequest request)
    {
        PriceByProductIdBrandIdAndDateCommand command = adapter.PriceByProductIdBrandIdAndDateRequestToDomain(request);
        Optional<Price> optionalPrice = priceUseCase.findPrice2(command);
        Optional<PriceResponse> optionalPriceResponse = optionalPrice.map(adapter::PriceToRessource);
        return optionalPriceResponse.orElse(null);

    }
}

