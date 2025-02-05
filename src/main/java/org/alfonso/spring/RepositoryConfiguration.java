package org.alfonso.spring;

import org.alfonso.application.ProductRepository;
import org.alfonso.infraestructure.repositories.product.ProductEntityAdapter;
import org.alfonso.infraestructure.repositories.product.ProductRepositoryInFile;
import org.alfonso.infraestructure.repositories.product.ProductRepositoryInMemory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class RepositoryConfiguration
{
    @Bean @Qualifier("inMemory")
    public ProductRepository productRepositoryInMemory(ProductEntityAdapter adapter)
    {
        return new ProductRepositoryInMemory(adapter);
    }

    @Bean @Qualifier("inFile")
    public ProductRepository productRepositoryInFile(ProductEntityAdapter adapter,@Qualifier("inFileDatasource") DataSource dataSource)
    {

        return new ProductRepositoryInFile(adapter,dataSource);
    }
}
