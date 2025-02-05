package org.alfonso.domain.price;

public class Currency
{
    private final CurrencyType currencyType;

    public Currency(CurrencyType currencyType) {
        this.currencyType = currencyType;
    }

    public CurrencyType getCurrencyType()
    {   return currencyType;}
}
