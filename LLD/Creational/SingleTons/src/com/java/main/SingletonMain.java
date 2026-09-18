package com.java.main;

public class SingletonMain {

	private static SingletonMain instance;

    private SingletonMain() {
        System.out.println("Object Created 1");
    }

    public static SingletonMain getInstance() {

    	System.out.println("Object Created 2");
        if (instance == null) {
            instance = new SingletonMain();
        }

        return instance;
    }
}