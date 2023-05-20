package me.fulln.study.alg;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class LeetCode20230417 {

    public static void main(String[] args) {
        Map<String,String > maps = new HashMap<>();
        maps.put("1","2");
        maps.put("2","3");
        maps.keySet().forEach(s ->{
            maps.put(s+"1",s+"2");
        });
        System.out.println(maps);
    }

}
