package org.alfonso.infraestructure.repositories.product;

import org.alfonso.MapOfListsGenerics;
import org.alfonso.MapOfListsGenerics3;
import org.alfonso.application.ProductRepository;
import org.alfonso.domain.price.Price;
import org.alfonso.domain.product.Product;
import org.alfonso.domain.product.ProductId;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class ProductRepositoryInFile implements ProductRepository
{
    private final DataSource dataSource;
    private final NamedParameterJdbcTemplate  namedJdbcTemplate;
    private final ProductEntityAdapter adapter;



    public ProductRepositoryInFile(ProductEntityAdapter adapter, DataSource dataSource)
    {
        this.dataSource = dataSource;
        this.namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.adapter = adapter;
    }


    @Override
    public void createProduct(Product product)
    {
        ProductEntity productEntity = adapter.toEntity(product);
        List<Price> priceList =  product.getPriceList();
        List<PriceEntity> priceEntities =  priceList.stream().map(adapter::toEntity).toList();

        MapSqlParameterSource productParams = new MapSqlParameterSource();
        productParams.addValue("productId", productEntity.getProductId());
        productParams.addValue("name", productEntity.getProductName());


        namedJdbcTemplate.update("INSERT INTO PRODUCT (ID,NAME) VALUES (:productId, :name)", productParams);

        for(PriceEntity priceEntity: priceEntities)
        {
            MapSqlParameterSource priceParams = new MapSqlParameterSource();
            priceParams.addValue("currency", priceEntity.getCurrency());
            priceParams.addValue("startDate", priceEntity.getStartDate());
            priceParams.addValue("endDate",priceEntity.getEndDate());
            priceParams.addValue("money",priceEntity.getMoney());
            priceParams.addValue("priority",priceEntity.getMoney());
            priceParams.addValue("brandId", priceEntity.getBrandId());
            priceParams.addValue("productId",productEntity.getProductId());

            namedJdbcTemplate.update("INSERT INTO PRICE (CURRENCY,STARTDATE,ENDDATE,MONEY,PRIORITY,BRAND_ID," +
                    "PRODUCT_ID VALUES (:currency,:startDate,:endDate,:money,:priority,:branId,:productId)", priceParams);
        }
    }

    @Override
    public Optional<Product> findByProductId(ProductId productId)
    {
        Optional<ProductEntity> productEntity = namedJdbcTemplate.query("SELECT * FROM PRODUCT WHERE ID = '" + productId.getValue() + "'", this::toProductEntity).stream().findFirst();
        List<PriceEntity>priceEntities = namedJdbcTemplate.query("SELECT * FROM PRICES WHERE PRODUCT_ID = '" + productId.getValue() + "'", this::toPriceEntity);
        return productEntity.map( it -> adapter.toDomain(it, priceEntities));
    }

    public Optional<Product> findBy(ProductId productId)
    {

        var params = new MapSqlParameterSource();
        params.addValue("productId", productId.getValue());

        Optional<ProductEntity>productEntityOpt = namedJdbcTemplate.query("SELECT * FROM PRODUCT WHERE ID = :productId", params, this::toProductEntity).stream().findFirst();
        List<PriceEntity>pricesEntities = namedJdbcTemplate.query("SELECT * FROM PRICES WHERE PRODUCT_ID = :productId", params, this::toPriceEntity);

        return productEntityOpt.map( productEntity -> adapter.toDomain(productEntity, pricesEntities) );
    }

    public List<Product> findAll2()
    {

        List<ProductEntity> productEntities = namedJdbcTemplate.query("SELECT * FROM PRODUCT", this::toProductEntity);
        Map<Integer,List<PriceEntity>> priceMap = namedJdbcTemplate.query("SELECT * FROM PRICES", this::toPriceEntity).stream()
            .collect(Collectors.groupingBy(PriceEntity::getProductId));

        return productEntities.stream()
                .map( it -> adapter.toDomain(it, priceMap.get(it.getProductId())))
                .toList();

    }

    @Override
    public List<Product> findAll()
    {
        List<ProductEntity> productEntities = namedJdbcTemplate.query("SELECT * FROM PRODUCT", this::toProductEntity);
        List<PriceEntity> priceEntities = namedJdbcTemplate.query("SELECT * FROM PRICES", this::toPriceEntity);
        MapOfListsGenerics3<Integer,PriceEntity> priceMap = toPriceMap(priceEntities);

        List<Product> result = new ArrayList<>();
        for(ProductEntity productEntity : productEntities)
        {
            List<PriceEntity> priceEntityList = priceMap.get(productEntity.getProductId());
            result.add(adapter.toDomain(productEntity, priceEntityList));
        }
        return  result;

    }

    public MapOfListsGenerics3<Integer,PriceEntity> toPriceMap (List<PriceEntity> priceEntities)
    {
        MapOfListsGenerics3<Integer,PriceEntity> map = new MapOfListsGenerics3<>();

        for(PriceEntity priceEntity : priceEntities)
        {
            map.add(priceEntity.getProductId(),priceEntity);
        }
        return map;
    }


    public List<Product> findAll4()
    {
        List<ProductEntity> productEntities = namedJdbcTemplate.query("SELECT * FROM PRODUCT", this::toProductEntity);
        List<PriceEntity> priceEntities = namedJdbcTemplate.query("SELECT * FROM PRICE", this::toPriceEntity);


        List<Product> result = new ArrayList<>();
        for (ProductEntity productEntity : productEntities)
        {
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("productId", productEntity.getProductId());

            List<PriceEntity>priceEntityList = namedJdbcTemplate.query("SELECT * FROM PRICE WHERE PRODUCT_ID = :productId",params,this:: toPriceEntity);
            result.add(adapter.toDomain(productEntity,priceEntityList));
        }
        return result;
    }

    public Optional<Product> findBy2(ProductId productId)
    {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("productId", productId);


        Optional<ProductEntity> productEntity = namedJdbcTemplate.query("SELECT * FROM PRODUCT WHERE PRODUCT_ID = : productId",params, this::toProductEntity).stream().findFirst();
        List<PriceEntity> priceEntities = namedJdbcTemplate.query("SELECT * FROM PRICE WHERE PRODUCT_ID = : productId", params, this::toPriceEntity);

        return productEntity.map(it->adapter.toDomain(it,priceEntities));



    }


    //PRIVATE METHODS---------------------------------------------------------------------------------------------------

    private ProductEntity toProductEntity(ResultSet resultSet, int rownum) throws SQLException
    {
        return new ProductEntity
                (
                        resultSet.getInt("ID"),
                        resultSet.getString("NAME")
                );
    }

    private PriceEntity toPriceEntity(ResultSet resultSet, int rownum) throws SQLException
    {

        return new PriceEntity
                (
                    resultSet.getInt("ID"),
                    resultSet.getString("CURRENCY"),
                    resultSet.getString("STARTDATE"),
                    resultSet.getString("ENDDATE"),
                    resultSet.getFloat("MONEY"),
                    resultSet.getInt("PRIORITY"),
                    resultSet.getInt("PRODUCTID")
                );
    }
}


