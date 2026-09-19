package com.java.test;

public class DuckTest {

	public static void main(String[] args) {

		System.out.println("-------MallardDuck---------");

		Duck mallard = new MallardDuck();

		mallard.display();
		mallard.performFly();
		mallard.performQuack();

		System.out.println("--------RubberDuck--------");

		Duck rubber = new RubberDuck();

		rubber.display();
		rubber.performFly();
		rubber.performQuack();

		System.out.println("-------DecoyDuck---------");

		Duck decoy = new DecoyDuck();

		decoy.display();
		decoy.performFly();
		decoy.performQuack();
	}
}