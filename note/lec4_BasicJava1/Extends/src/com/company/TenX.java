package com.company;

public class TenX implements IIntUnaryFunction {

    /** Return ten times its argument */
    @Override
    public int apply(int x) {
        return 10 * x;
    }
}
