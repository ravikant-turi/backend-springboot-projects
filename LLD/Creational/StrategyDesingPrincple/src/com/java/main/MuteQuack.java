package com.java.main;

public class MuteQuack implements QuackBehavior {

    @Override
    public void quack() {
        System.out.println("I am silent");
    }
}