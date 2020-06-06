package com.company;

/** Demonstrate high order functions in Java */
public class HoFDemo {
    public static int do_twice(IIntUnaryFunction f, int x) {    // static is for avoiding instantiate the class
        return f.apply(f.apply(x));
    }

    public static void main(String[] args) {
        IIntUnaryFunction tenX = new TenX();    // TenX is name of its class, but do_twice is expecting an object -> need instantiate the object
        System.out.println(do_twice(tenX, 2));
    }
}
