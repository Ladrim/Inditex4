package org.alfonso.domain.price;

public class Money
{
    private final Float value;


    public Money(Float value)
    {
        this.value = value;
    }

    public Float getValue()
    {   return value;}

    @Override
    public String toString() {
        return "Money{" +
                "value=" + value +
                '}';
    }
}
