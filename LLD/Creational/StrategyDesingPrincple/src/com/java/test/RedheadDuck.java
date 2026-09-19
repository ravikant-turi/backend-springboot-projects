package com.java.test;

import com.java.main.FlyWithWings;
import com.java.main.Quack;

public class RedheadDuck extends Duck {

    public RedheadDuck() {
        flyBehavior = new FlyWithWings();
        quackBehavior = new Quack();
    }

    @Override
    public void display() {
        System.out.println("I am a Redhead Duck");
    }
}