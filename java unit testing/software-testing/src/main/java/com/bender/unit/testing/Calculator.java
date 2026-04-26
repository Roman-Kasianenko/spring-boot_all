package com.bender.unit.testing;

public class Calculator {

    public <T extends Number> double add(T a, T b) {
        return a.doubleValue() + b.doubleValue();
    }
}
