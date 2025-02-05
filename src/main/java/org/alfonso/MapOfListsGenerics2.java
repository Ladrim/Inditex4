package org.alfonso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapOfListsGenerics2 <KEY,SUBVALUE>
{
    private Map<KEY,List<SUBVALUE>> mapOfLists = new HashMap<>();

    public List<SUBVALUE> getInnerList (KEY key)
    {
        return mapOfLists.get(key);
    }

    public void addSubvalue(KEY key, SUBVALUE subvalue)
    {
        List<SUBVALUE> innerList = getInnerList(key);
        if (innerList == null)
        {
            List<SUBVALUE> subvalueList = new ArrayList<>();
            subvalueList.add(subvalue);
            mapOfLists.put(key,subvalueList);
        }
        else
        {
            innerList.add(subvalue);
        }
    }
}
