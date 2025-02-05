package org.alfonso.domain.product;

import java.util.Objects;

public class ProductId
{
    private final Integer value;

    public ProductId(Integer value)
    {   this.value = value;}

    public Integer getValue()
    {   return value;}

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProductId productId = (ProductId) o;
        return Objects.equals(value, productId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
