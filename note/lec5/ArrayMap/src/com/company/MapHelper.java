package com.company;

import org.junit.Test;

import java.awt.image.ImageProducer;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MapHelper {

    /* get(Key) : Return item in map if it exists. */
    /* Use the implementation of generic methods to accomplish this method */
    public static <K, V>  V get(IMap61B<K, V> sim, K key) {    // K, V not sharing to other parts of the code
//        public static Integer get(IMap61B<String, Integer> sim, String key) {
        if (sim.containsKey(key)) {
            return sim.get(key);
        }
        return null;
    }
    /* maxKey() : Returns max of all keys. Works only if x and y have comparable data. */
    public static <K, V> K getMaxKey(IMap61B<K, V> sim) {
        List<K> keyList = sim.keys();
        K tempMaxKey = keyList.get(0);
        V tempMaxVal = sim.get(tempMaxKey);
        for (int i = 0; i < keyList.size(); i++) {
            if ((int) sim.get(keyList.get(i)) > (int) tempMaxVal) {
                tempMaxVal = sim.get(keyList.get(i));
                tempMaxKey = keyList.get(i);
            }
        }
        return tempMaxKey;
    }

//    public static <K extends Comparable<K>, V> K getMaxKey(IMap61B<K, V> map) {
//        List<K> keylist = map.keys();
//        K largest = keylist.get(0);
//        for (K k : keylist) {
//            if (k.compareTo(largest)) {
//                largest = k;
//            }
//        }
//        return largest;
//    }

    @Test
    public void testGet(){
        IMap61B<String, Integer> m = new ArrayMap<String, Integer>();
        m.put("horse", 3);
        m.put("fish", 9);
        m.put("house", 10);

        Integer actual = MapHelper.get(m, "fish");
        Integer expected = 9;
        assertEquals(expected, actual);
        assertEquals(null, MapHelper.get(m, "awejfl;nbaf"));
    }

    @Test
    public void testMaxKey() {
        IMap61B<String, Integer> m = new ArrayMap<String, Integer>();
        m.put("horse", 3);
        m.put("fish", 9);
        m.put("house", 10);
        m.put("house2", 5);

        String actual = MapHelper.getMaxKey(m);
        String expected = "house";
        assertEquals(expected, actual);
    }
}
