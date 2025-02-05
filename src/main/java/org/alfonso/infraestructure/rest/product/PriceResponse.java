package org.alfonso.infraestructure.rest.product;

public class PriceResponse
{
    private final Integer brandId;
    private final String currency;
    private final String startDate;
    private final String endDate;
    private final Float money;
    private final Integer priority;

    public PriceResponse(Integer brandId, String currency, String startDate, String endDate, Float money, Integer priority)
    {
        this.brandId = brandId;
        this.currency = currency;
        this.startDate = startDate;
        this.endDate = endDate;
        this.money = money;
        this.priority = priority;
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
    {   return money;}

    public Integer getPriority()
    {   return priority;}
}
