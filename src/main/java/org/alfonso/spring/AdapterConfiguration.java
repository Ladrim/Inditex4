package org.alfonso.spring;

import org.alfonso.infraestructure.repositories.product.ProductEntityAdapter;
import org.alfonso.infraestructure.rest.product.ProductRessourceAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdapterConfiguration
{
    @Bean
    public ProductEntityAdapter productEntityAdapter()

    {
        return new ProductEntityAdapter();
    }

    @Bean
    public ProductRessourceAdapter productRessourceAdapter()

    {
        return new ProductRessourceAdapter();
    }
}
