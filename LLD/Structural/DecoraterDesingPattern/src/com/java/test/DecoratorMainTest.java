package com.java.test;

import com.java.main.CheeseDecorator;
import com.java.main.FarmHousePizza;
import com.java.main.MushroomDecorator;
import com.java.main.OliveDecorator;
import com.java.main.Pizza;

public class DecoratorMainTest {

	public static void main(String[] args) {

		Pizza pizza = new FarmHousePizza();

//		pizza = new CheeseDecorator(pizza);

//		pizza = new MushroomDecorator(pizza);

		pizza = new OliveDecorator(pizza);

		System.out.println("Description : " + pizza.getDescription());
		System.out.println("Cost : " + pizza.cost());
	}
}