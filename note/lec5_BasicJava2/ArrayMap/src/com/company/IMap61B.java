package com.company;

import java.util.List;

public interface IMap61B<K, V> {
    public void put(K key, V value);
    public boolean containsKey(K key);
    public V get(K key);
    public List<K> keys();
    public int size();
}
