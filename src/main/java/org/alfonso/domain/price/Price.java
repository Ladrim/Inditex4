package org.alfonso.domain.price;

import java.util.Objects;

public class Price
{
    private final BrandId brandId;
    private final Currency currency;
    private final DateInRange dateInRange;
    private final Money money;
    private final Priority priority;

    public Price(BrandId brandId, Currency currency, DateInRange dateInRange, Money money, Priority priority) {
        this.brandId = brandId;
        this.currency = currency;
        this.dateInRange = dateInRange;
        this.money = money;
        this.priority = priority;
    }

    public BrandId getBrandId()
    {   return brandId;}

    public Currency getCurrency()
    {   return currency;}

    public DateInRange getDateInRange()
    {   return dateInRange;}

    public Money getMoney()
    {   return money;}

    public Priority getPriority()
    {   return priority;}

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Price price = (Price) o;
        return Objects.equals(brandId, price.brandId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(brandId);
    }

    @Override
    public String toString() {
        return "Price{" +
                "brandId=" + brandId +
                ", currency=" + currency +
                ", dateInRange=" + dateInRange +
                ", money=" + money +
                ", priority=" + priority +
                '}';
    }
}
