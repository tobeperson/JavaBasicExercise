package com.company.tobeace;

import java.util.*;

public class HashMapDemo {
    public static void main(String[] args) {
        testHashMap();
        System.out.println("==============");
        testLinkedHashMap();
        System.out.println("==============");
        testTreeMap();
    }
    private static void testHashMap() {
        Map<String,String> hashMap=new HashMap<>();
        hashMap.put("offer1","蚂蚁金服");
        hashMap.put("offer2","哔哩哔哩");
        hashMap.put("offer3","ebay");
        Set<Map.Entry<String,String>> set=hashMap.entrySet();
        Iterator<Map.Entry<String,String>> iterator= set.iterator();
        while (iterator.hasNext()) {
            Map.Entry entry=iterator.next();
            String key=(String) entry.getKey();
            String value=(String)entry.getValue();
            System.out.println("HashMap: "+"key= "+key+" value= "+value);
        }
    }

    private static void testLinkedHashMap() {
        Map<String,String> linkedHashMap =new LinkedHashMap<>(16,0.75f);
        linkedHashMap.put("offer1","蚂蚁金服");
        linkedHashMap.put("offer2","哔哩哔哩");
        linkedHashMap.put("offer3","ebay");
        System.out.println("开始时顺序：");
        Set<Map.Entry<String,String>> set=linkedHashMap.entrySet();
        Iterator<Map.Entry<String,String>> iterator= set.iterator();
        while (iterator.hasNext()) {
            Map.Entry entry=iterator.next();
            String key=(String) entry.getKey();
            String value=(String)entry.getValue();
            System.out.println("LinkedHashMap: "+"key= "+key+" value= "+value);
        }
        System.out.println("通过get方法，导致key为offer2到表尾");
        linkedHashMap.get("offer3");
        Set<Map.Entry<String,String>> set2=linkedHashMap.entrySet();
        Iterator<Map.Entry<String,String>> iterator2= set2.iterator();
        while (iterator2.hasNext()) {
            Map.Entry entry=iterator2.next();
            String key=(String) entry.getKey();
            String value=(String)entry.getValue();
            System.out.println("LinkedHashMap: "+"key= "+key+" value= "+value);
        }
    }

    private static void testTreeMap() {
        Map<String,String> treeMap=new TreeMap<>(new xbComparator());
        treeMap.put("offer1","蚂蚁金服");
        treeMap.put("offer2","哔哩哔哩");
        treeMap.put("offer3","ebay");
        System.out.println("开始时顺序：");
        Set<Map.Entry<String,String>> set=treeMap.entrySet();
        Iterator<Map.Entry<String,String>> iterator= set.iterator();
        while (iterator.hasNext()) {
            Map.Entry entry=iterator.next();
            String key=(String) entry.getKey();
            String value=(String)entry.getValue();
            System.out.println("TreeMap: "+"key= "+key+" value= "+value);
        }
    }
    static class xbComparator implements Comparator{
        public int compare(Object o1,Object o2){
            String i1=(String)o1;
            String i2=(String)o2;
            return i1.compareTo(i2);
        }
    }
}
