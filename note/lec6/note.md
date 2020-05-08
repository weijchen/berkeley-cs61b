---
# Lecture 6
---

## Lists and Sets in Java
- Sets: 
  - Stores a set of values with no duplicates
  - Has no sense of order

## Exceptions
- For breaking the normal flow of control when error occur
```java
Dog d = new Dog("Lucy", "Retriever", 80);
d.becomeAngry();

try {
  d.receivePat();
} catch (Exception e) {
  System.out.println("Tried to pat: " + e); 
}
// In this case, the code does not crash
```
- a good code with exception handler should be like this
```java
func readFile: {
    try {
        open the file;
        determine its size;
        allocate that much memory;
        read the file into memory;
        close the file;
    } catch (fileOpenFailed) {
        doSomething;
    } catch (sizeDeterminationFailed) {
        doSomething;
    } catch (memoryAllocationFailed) {
        doSomething;
    } catch (readFailed) {
        doSomething;    
    } catch (fileCloseFailed) {
        doSomething;
    }
}
```

- Checked or unchecked exceptions:
  - For checked exceptions, exception must be caught or specified

## Iterable
- ugly version: use Iterator

## Object Methods
```java
String toString()           // provides a string representation of an object
boolean equals(Object obj)
int hashCode()
```
- toString() in Object is the name of the class, then an @ sign, then the memory location of the object
- If we use string concatenation, it takes quadratic time; if we use StringBuilder, it takes linear time.
- Equals vs. ==: these two behave differently
  - == compares the bits, for references, == means "referencing the same object."
  - .equals is used for classes
