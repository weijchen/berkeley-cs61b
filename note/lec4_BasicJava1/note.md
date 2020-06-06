---
# Lecture 4
---
## Reminder: Dynamic Type Selection
- For run-time type of variables: choose dynamic-type's method if the method was overridden
- As for compile-time type of variables: always choose static-type's method

## Compile-Time Types and Expressions
- An expression using the new keyword has the specified compile-time type -> **checking the "is-a" expression**
```java
SLList<Integer> sl = new VengefulSLList<Integer>();
```
- Methods call
```java
public static Dog maxDog(Dog d1, Dog d2) {};    // Any call to maxDog will have compile-time type Dog!

Poodle frank = new Poodle("Frank", 5);
Poodle frankJr = new Poodle("Frank Jr.", 15);

Dog largerDog = maxDog(frank, frankJr); // true
Poodle largerPoodle = maxDog(frank, frankJr);   // compilation error (because their compile-time type is Dog) -> think of A Dog is not always a Poodle
```
- using Casting can enforce the compile-time type of any expression, but could be dangerous. (Casting telling the compiler not to do type-checking)
```java
Poodle largerPoodle = (Poodle) maxDog(frank, frankJr);
```

## Higher Order Functions: a fuction that treats other functions as data
- in Python
```python
def tenX(x):
    return 10*x
def do_twice(f, x):
    return(f(f(x))) # print(do_twice(tenX, 2)) become 10*(10*2)
```
- in Java
  - Previous version (Java 7 and earlier) has no class for function
```java
public class TenX implements IntUnaryFunction { // to represent a function
    /* Returns ten times the argument. */
    public int apply(int x) {
        return 10 * x;
    }
}

public static int do_twice(IntUnaryFunction f, int x) {
    return f.apply(f.apply(x))
}
System.out.println(do_twice(new TenX(), 2));
```

## Polymorphism: providing a single interface to entities of different types
- how an object can be regarded as an instance of its own class/superclass/superclass's superclass
- ex: arraylist, linkedlist, sslinkedlist, ..., etc. Al have addFirst() -> behave based on the dynamic type
```python
# Explicit HoF approach: sometimes called a "callback", which is the helping function
def print_larger(x, y, compare, stringify):
    if compare(x, y):
        return stringify(x)
    return stringify(y)

# Subtype Polymorphism approach: define the output of the method based on the given object
def print_larger(x, y):
    if x.largerThan(y):
        return x.str()
    return y.str()
```
## Max function problems
```java
public static Object max(Object[] items) {
    int maxDex = 0;
    for (int i = 0; i < items.length; i += 1) {
        if (items[i] > items[maxDex]) { // here is where the problem occur: we cannot assure the objects we want to compare, e.q., int and str
            maxDex = i;
        }
    }
    return items[maxDex];
}

public static void main(String[] args) {
    Dog[] dogs = {new Dog("Elyse", 3), new Dog("Sture", 9), new Dog("Benjamin", 15)};
    Dog maxDog = (Dog) max(dogs);
    maxDog.bark();
}
```
- cons: the issue of redundant codes, and the dream of one universal method
- solution: **create an interface** that guarantees a comparison method
```java
public interface OurComparable {
    public int compareTo(Object o);
}
public class Dog implements OurComparable {
    // but need to define size first!
    public int compareTo(Object o) {
        // if this.size < o.size ... then ...
        // elif this.zie == o.size ... then ...
        // else ... then ...
        Dog uddaDog = (Dog) o;  // this could raise run-time error if the object casting generage error
        // Or use the difference betweene this.size and o.size as the comparison basis
        return this.size - o.size;
    }
}
```

## Better version of OurComparable
- cons: 
  - awkward casting to/from Objects
  - No existing classes implement this method -> useless if we want to apply to other classes
- solution:
```java
public interface Comparable<T> {
    public int compareTo(T obj);
}

// in Dog.java
public class Dog implements Comparable<Dog> {
    ...
    public int compareTo(Dog uddaDog) {
    return this.size - uddaDog.size;
    }
}
```

## Abstract Data Types (ADTs)

## Java Libraries
- Three most important ADTs in the java.util (Collection):
  - List: ArrayList, LinkedList
  - Set: HashSet, TreeSet
  - Map: HashMap, TreeMap

## Java vs. Python
### Sets implementation
- in Python
```python
num_legs = {"horse": 4, "dog": 4, "human": 2, "fish": 0}
```
- in Java
```java
Map<String, Integer> numLegs = new TreeMap<>();
numLegs.put("horse", 4);
numLegs.put("dog", 4);
numLegs.put("human", 2);
numLegs.put("fish", 0);
```
- However, in Java, programmers can decide which *implementation* of an abstract data type that they want to use. For example: HashMap (faster) or TreeMap (in alphabetical order)

## Reminder: inheritance
- interface inheritance: What (the class can do)
- implementation inheritance: How (the class does it)
- For methods in interface: they all must be public, so we don't need to specify the keyword '***public***'; and they will be abstract method unless we use the keyword '***default***'
- For variables in interface: they are '***public static final***'

## Abstract Classes: an intermediate level between classes and interfaces
- Can provide either abstract or concrete methods
- Can provide variables
- But subclasses can only extends one abstract class
```java
public abstract class GraphicObject {
    public int x, y;
    public void moveTo(int newX, int newY) { ... }
    public abstract void draw();
    public abstract void resize();
}

public class Circle extends GraphicObject {
    // no need to implement moveTo since it is already in GraphicObject
    public void draw() { ... }
    public void resize() { ... }
} 
```

## Canonicalization and Packages
- Canonical representation: A unique representation for a thing
- Package name give a canonical name for everything