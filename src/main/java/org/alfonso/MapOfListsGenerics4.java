package org.alfonso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapOfListsGenerics4<KEY,SUBVALUE>
{
    private Map<KEY,List<SUBVALUE>> mapOfLists = new HashMap<>();

    public List<SUBVALUE> getInnerList (KEY key)
    {
        return mapOfLists.getOrDefault(key,new ArrayList<>());
    }

    public void addSubvalue(KEY key, SUBVALUE subvalue)
    {
        List<SUBVALUE> innerList = getInnerList(key);

        mapOfLists.put(key,innerList);
    }


    public List<SUBVALUE> getValues()
    {
       return mapOfLists.values().stream().flatMap(it -> it.stream()).toList();
    }

}
