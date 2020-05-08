---
# Lecture 5
---
## Java Syntax
- Autoboxing, promotion, immutability, generics
- Exceptions, access control
- Iterables/iterators, equals
- Wildcards, type upper bounds, covariance

## Generics
- requiring provide one or more ***actual type arguments***
- Primitive types cannot used as actual type arguments (int, short, float, char...)
- Autoboxing (auto-unboxing): implicit conversions between wrapper/primitives 
```java
public class BasicArrayList {
    public static void main(String[] args) {
      ArrayList<Integer> L = new ArrayList<Integer>();
      L.add(new Integer(5));
      L.add(new Integer(6));

      /* Use the Integer.valueOf method to convert to int */
      int first = L.get(0).valueOf();
    }
}

// With autoboxing/auto-unboxing, java can
public class BasicArrayList {
    public static void main(String[] args) {
      ArrayList<Integer> L = new ArrayList<Integer>();
      L.add(5);
      L.add(6);
      int first = L.get(0);
    }
}
```
- Wrapper types are computational expensive and requiring more memory

## Immutable Data Types
- Example:
  - Mutable: ArrayDeque, Planet
  - Immutable: Integer, String, Date, 'final' keyword
- Pros: less to think about, avoids bugs and makes debugging easier
- Cons: must create a new object anytime anything changes
- Declaring a reference as **Final** does not make object immutable, for example, we can add item to a final ArrayList

## Generic Methods
- Create a method that operates on generic types by defining type parameters before the return type of the method
```java
public static <K, V>  V get(IMap61B<K, V> sim, K key) {    // K, V not sharing to other parts of the code
        if (sim.containsKey(key)) {
            return sim.get(key);
        }
        return null;
    }
```

## Summary
- Autoboxing/auto-unboxing of primitive wrapper types
- Promotion between primitive types; in java, so called "Conversion" -> widen from int to long
- Specification of generic types for methods (before return type) (e.g., public static <K, V> K getMaxKey())
- Type upper bounds (e.g., K extends Comparable<K>)
- 