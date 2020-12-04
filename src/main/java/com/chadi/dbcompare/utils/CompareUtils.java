package com.chadi.dbcompare.utils;

import java.util.HashMap;
import java.util.Map;

public class CompareUtils {

    public static Map getPropertyToMap(String key){

        Map map = new HashMap();
        String property = PropertyUtils.getProperty(key);
        String[] split = property.split(",");

        for (String s : split) {

            String[] split1 = s.split("=");
            if(split1.length == 2){
                map.put(split1[0], split1[1]);
            }
        }
        return map;
    }
}
