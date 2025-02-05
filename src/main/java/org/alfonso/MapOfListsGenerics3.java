package org.alfonso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapOfListsGenerics3<KEY,SUBVALUE>
{
    private Map<KEY, List<SUBVALUE>> mapOfLists = new HashMap<>();

    public List<SUBVALUE> get (KEY key)
    {
        return mapOfLists.getOrDefault(key,new ArrayList<>());
    }

    public void add (KEY key, SUBVALUE subvalue)
    {
        List<SUBVALUE> innerList = mapOfLists.computeIfAbsent(key, it -> new ArrayList<>());
        innerList.add(subvalue);
    }


}
