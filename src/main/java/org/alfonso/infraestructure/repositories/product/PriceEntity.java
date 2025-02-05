package org.alfonso.infraestructure.repositories.product;

public class PriceEntity
{
    private final Integer brandId;
    private final String currency;
    private final String startDate;
    private final String endDate;
    private final Float money;
    private final Integer priority;
    private final Integer productId;

    public PriceEntity(Integer brandId, String currency, String startDate, String endDate, Float money, Integer priority,
                       Integer productId)
    {
        this.brandId = brandId;
        this.currency = currency;
        this.startDate = startDate;
        this.endDate = endDate;
        this.money = money;
        this.priority = priority;
        this.productId = productId;
    }

    public Integer getBrandId()
    {   return brandId;}

    public String getCurrency()
    {   return currency;}

    public String getStartDate()
    {   return startDate;}

    public String getEndDate()
    {   return endDate;}

    public Float getMoney()
    {    return money;}

    public Integer getPriority()
    {   return priority;}

    public Integer getProductId()
    {   return productId;}

    //HAY UN MEGABUUUGGGGGGGGGG!!!!!!!!!!!!!!!!!!!!!
}
