---
Lecture 8: Efficient Programming 
---

## Efficient programming comes in two flavors:
- Programming cost: develop, read, modify, and maintain
- Execution cost: execute, memory

## Encapsulation:
- Two terms:
    - Module: a set of methods that work together as as whole to perform some task or set of related tasks
    - Encapsulated: A module is said to be encapsulated if its implementation is completely hidden, and it can be accessed only through a documented interface

## API's: Application Programming Interface
- the list of constructors and methods and a shopr description of each
- consists of syntactic and semantic specification

## ADT's: Abstract Data Structures
- high-level types that are defined by their behaviors, not their implemetation.

## Example of Algorithm Cost:
- Objective Determine if a sorted array contains any duplicates
- How to characterize the runtimes of different algorithms
    - should be simple and mathematically rigorous
    - should demonstrate superiority
    
For example: 
1. timer (simulation):
    - pros: easy to measure, meaning is obvious
    - cons: require large amounts of computation time, result varies with machine, compiler, input data
2. count possible operations:
    - pros: machine independent
    - cons: tedious to compute, arbitrary array size, no actual time
    
3. Count possible operations in terms of input array size N:
    - pro: machine independent, tells the algorithm scales
```java
for (int i = 0; i < A.length; i += 1) {
    for (int j = i + i; j < A.length; j += 1) {
       if (A[i] == A[j]) {
        return true;
        }
    }
}
return false;
//                      N = 10000       symbolic count
// i = 0                1               1
// j = i + 1            1 to 10000      1 to N
// less than (<)        2 to 50015001   2 to (N^2+3N+2)/2
// increment (+=1)      0 to 5000500    0 to (N^2+N)/2
// equals (==)          1 to 49995000   1 to (N^2-N)/2
// array accesses       2 to 99990000   2 to N^2-N
```
- In most cases, we care only about asymptotic behavior, i.e. *what happens for very large N*
- We refer the "shape" of a runtime function as its ***order of growth***.

So, how to characterize the runtimes of functions:
1. Consider only the worst case
2. Restrict attention to one operation (the most critical one)
   1. good choice: increment -> the choice is called **cost model**
3. Eliminate low order terms
4. Eliminate multiplicative constants
![img](order.png) -> think geometrically (the area)

## Two ways of approaching our runtime analysis
- Counting the number of operations
- Geometric argument 

## Another example
```java
public static void printParty(int N) {
   for (int i = 1; i <= N; i = i * 2) {
      for (int j = 0; j < i; j += 1) {
         System.out.println("hello");   
         int ZUG = 1 + 1;
      }
   }
}
```
![img](ex2.png)
= $C(N) = 1 + 2 + 4 + ... + N (if N is a power of 2).$

In sum, two important sum to memorize:
1. $1 + 2 + 3 + ... + Q = Q(Q+1)/2 = \Theta(Q^2)$ (Sum of First Natural Numbers)
2. $1 + 2 + 4 + ... + Q = 2Q - 1 = \Theta(Q)$ (Sum of First Powers of 2)

## Recursion
```java
public static int f3(int n) {
   if (n <= 1) 
      return 1;
   return f3(n-1) + f3(n-1);
}
```
$C(1)=1; C(2) = 1 + 2C(2)=1+2; C(3) = 1 + 2 + 4C(3)=1+2+4$
$C(N)=1 + 2 + 4 + ... + 2^N - 1$

Applying this formula to the sum of $1 + 2 + 4 + ... + Q = 2Q - 1 = \Theta(Q)$

## Binary Search
- Compare key against middle entry
  - Too small, go left
  - Too big, go right
  - Equal, found
- Order of growth of the worst case runtime
  - $log_2 N$
  - Problem size halves over and over until it gets down to 1
  - Think of $C$ as the time we need to cut the array into half: $1 = (N/2)^C$ -> $C = log_2 N$ 
  - Exact count = $C = log_2 N \text{(floor)} + 1$
- Requires the list to be in sorted order
- Logarithm is really good!
![img](log.png)

## Selection Sort
- Find the smallest unfixed item, move it to the front, and 'fix' it
- Sort the remaining unfixed items using selection sort
- Runtime of selection sort is $\Theta(N^2)$, triangle area

Arbitrary Unit of Time: for thinking under a general sense of time
## Merge Sort
- Merging: 
  - Having two sorted arrays that we want to combine into a single big sorted array.
  - Runtime: $\Theta(N)$, depending on the length of the array
![img](merge.png)
- Mergesort does merges all the way down (divide and conquer)
  - Runtime: $\Theta(N \log N)$
  - For k layers with size N list, k is a function of N: $log_2 N$
  - Since total runtime is Nk, the final outcome is $\Theta(N log_2 N)$
![img](mergesort.png)


## Runtime Analysis Subtleties
- Big Theta $\Theta$ expresses exact order of growth for runtime in terms of N
  - If runtime depends on more factors than N, may need different standard.

## Big Theta vs. Big O
- Big Theta tells the worst senario while Big O tells the runtime range of given algorithm
  - For example, printArray and traversing 2d matrix belong to $O(N^2)$
- The usefulness of Big O:
  - Simpler statements
  - Sometimes don't know the exact runtime, so use O to give an upper bound
  - Easier to prove

## Formalizing Order of Growth
- Big-Theta: 
  - $R(N) \in \Theta(f(N))$ means $k_1 * f(N) \leq R(N) \leq k_2 * f(N)$ for two positive constants $k_1$ and $k_2$
  - Examples:
    - $N^3 + 3N^4 \in \Theta(N^4)$
- Big-Omega: 
  - $R(N) \in \Omega(f(N))$ means $k_1 * f(N) \leq R(N)$ for positive constant $k_1$
  - Big Theta: thought of as "equals"
  - Big Omega: thought of as "greater than or equal"
    - $N^3 + 3N^4 \in \Theta(N^4)$
    - $N^3 + 3N^4 \in \Omega(N^4)$
    - $N^3 + 3N^4 \in \Omega(N^3)$
    - $N^3 + 3N^4 \in \Omega(\log N)$
    - $N^3 + 3N^4 \in \Omega(1)$
- Big-O:
  - $R(N) \in O(f(N))$ means $R(N) \leq k_1 * f(N)$ for positive constant $k_1$
- If $R(N) = O(f(N))$ and $R(N) = \Omega(f(N))$, then $R(N) = \Theta(f(N))$, this is often been used to prove algorithm's runtime
- Common conceptual errors:
  - Big O does NOT mean worst case
  - Big Omega does NOT mean best case
  
![img](three_order.png)

## Amortized Analysis
- Resizes to accommodate additional entries:
  - When the array inside the ArrayList is full, double in size
  - Most add operations are constant time, but some are very expensive
  - An aspect to view these problem is using average runtime cost of the algorithm