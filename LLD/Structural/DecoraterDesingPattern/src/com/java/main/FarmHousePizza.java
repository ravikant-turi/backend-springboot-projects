package com.java.main;

public class FarmHousePizza implements Pizza {

    @Override
    public String getDescription() {
        return "Farm House Pizza";
    }

    @Override
    public double cost() {
        return 100;
    }
}
