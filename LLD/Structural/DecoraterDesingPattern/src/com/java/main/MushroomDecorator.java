package com.java.main;
public class MushroomDecorator extends ToppingDecorator {

    public MushroomDecorator(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Mushroom";
    }

    @Override
    public double cost() {
        return pizza.cost() + 30;
    }
}