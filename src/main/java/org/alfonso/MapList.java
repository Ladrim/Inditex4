package org.alfonso;

import org.alfonso.infraestructure.repositories.product.PriceEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapList
{
    private Map<Integer, List<PriceEntity>> map = new HashMap<>();

    public void addPriceEntity(PriceEntity priceEntity)
    {
        List<PriceEntity> getInnerList = map.get(priceEntity.getProductId());

        if(getInnerList == null)
        {
            List<PriceEntity> priceEntityList = new ArrayList<>();
            priceEntityList.add(priceEntity);
            map.put(priceEntity.getProductId(),priceEntityList);
        }
        else
        {
            getInnerList.add(priceEntity);
        }
    }
}
