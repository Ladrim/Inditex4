package org.alfonso.spring;

import org.alfonso.application.PriceUseCase;
import org.alfonso.application.ProductRepository;
import org.alfonso.application.ProductUseCase;
import org.alfonso.infraestructure.repositories.product.ProductEntityAdapter;
import org.alfonso.infraestructure.repositories.product.ProductRepositoryInFile;
//import org.alfonso.infraestructure.repositories.product.ProductRepositoryInMemory;
import org.alfonso.infraestructure.rest.product.ProductRessource;
import org.alfonso.infraestructure.rest.product.ProductRessourceAdapter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class BeanConfiguration
{
    @Bean
    public ProductRessource productRessource(ProductRessourceAdapter adapter, PriceUseCase priceUseCase, ProductUseCase productUseCase)
    {
        return  new ProductRessource(adapter, priceUseCase, productUseCase);
    }

    @Bean
    public ProductUseCase productUseCase(@Qualifier("inFile") ProductRepository productRepository)
    {
        return new ProductUseCase(productRepository);
    }

    @Bean
    public PriceUseCase priceUseCase(@Qualifier("inFile") ProductRepository productRepository)
    {
        return new PriceUseCase(productRepository);
    }
}
