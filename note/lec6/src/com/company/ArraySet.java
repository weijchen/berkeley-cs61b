package com.company;

import java.lang.reflect.Array;
import java.util.*;

public class ArraySet<T> implements Iterable<T> {
    private T[] values;
    private int size;

    public ArraySet() {
        values = (T[]) new Object[100];
        size = 0;
    }

    /* Associates the specified value with the specified key in this map. Throws an IllegalArgumentException if the key is null. */
    public void add(T x) {
        if (x == null) {
            throw new IllegalArgumentException("You cannot add null into the arrayset!");
        }
        if (contains(x)) {
            return;
        }
        values[size] = x;
        size += 1;
    }

    private int getIndex(T x) {
        for (int i = 0; i < size; i += 1) {
            if (values[i].equals(x)) {
                return i;
            }
        }
        return -1;
    }
    /* Returns true if this map contains a mapping for the specified key. */
    public boolean contains (T x) {
        return getIndex(x) > -1;
    }

    public int size() {
        return size;
    }

    /*
    @Override
    public String toString() {
        StringBuilder returnString = new StringBuilder("{");
        for (T item : this) {
            returnString.append(item.toString());
            returnString.append(", ");
        }
        returnString.append(" }");
        return returnString.toString();
    }
    */

    /* Better solution of toString*/
    @Override
    public String toString() {
        List<String> listOfItems = new ArrayList<>();
        for (T x : this) {
            listOfItems.add(x.toString());
        }
        return "{" + String.join(",", listOfItems) + "}";
    }

    public static <Glerp> ArraySet<Glerp> of(Glerp... stuff) {
        ArraySet<Glerp> returnSet = new ArraySet<>();
        for (Glerp x : stuff) {
            returnSet.add(x);
        }
        return returnSet;
    }

    @Override
    public boolean equals(Object other) {       // allow for a more generic class
        if (this == other) {        // optimization
            return true;
        }
        if (other == null) {
            return false;
        }
        if (other.getClass() != this.getClass()){
            return false;
        }
        ArraySet<T> o = (ArraySet<T>) other;
        if (o.size() != this.size()) {
            return false;
        }
        for (T item : this) {
            if (!o.contains(item)) {
                return false;
            }
        }
        return true;
    }

    public void printSet() {
        for (T val : values) {
            System.out.println(val + " ");
        }
        System.out.println("");
    }

    public Iterator<T> iterator() {
        return new ArraySetIterator();
    }

    private class ArraySetIterator implements Iterator<T> {
        private int wizPos;
        public ArraySetIterator() {
            wizPos = 0;
        }
        public boolean hasNext() {
            return wizPos < size;
        }
        public T next() {
            T returnItem = values[wizPos];
            wizPos += 1;
            return returnItem;
        }
    }

    public static void main(String[] args) {
        ArraySet<Integer> javaset = new ArraySet<>();
        javaset.add(5);
        javaset.add(23);
        javaset.add(42);

        Iterator<Integer> seer = javaset.iterator();

        while (seer.hasNext()) {
            System.out.println(seer.next());
        }

        for (int i : javaset) {
            System.out.println(i);
        }

        System.out.println(javaset.toString());

        ArraySet<Integer> aset2 = new ArraySet<>();
        aset2.add(5);
        aset2.add(23);
        aset2.add(42);
        System.out.println(javaset.equals(aset2));

        ArraySet<String> asetOfStrings = ArraySet.of("hi", "I'm", "here");
        System.out.println(asetOfStrings);
    }
}
