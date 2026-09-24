package com.java.main;
public class OliveDecorator extends ToppingDecorator {



    public OliveDecorator(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Olive";
    }

    @Override
    public double cost() {
        return pizza.cost() + 30;
    }
}