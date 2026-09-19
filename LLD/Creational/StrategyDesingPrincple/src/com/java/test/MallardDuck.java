package com.java.test;

import com.java.main.FlyWithWings;
import com.java.main.Quack;

public class MallardDuck extends Duck {

	public MallardDuck() {
		flyBehavior = new FlyWithWings();
		quackBehavior = new Quack();
	}

	@Override
	public void display() {
		System.out.println("I am a Mallard Duck");
	}
}