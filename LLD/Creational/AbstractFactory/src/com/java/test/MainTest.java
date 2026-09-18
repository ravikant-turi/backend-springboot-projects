package com.java.test;

import com.java.main.Chair;
import com.java.main.FurnitureFactory;
import com.java.main.ModernFurnitureFactory;
import com.java.main.RegularFurnitureFactory;
import com.java.main.Sofa;
import com.java.main.Table;

public class MainTest {

	public static void main(String[] args) {

		// Regural

		System.out.println("=======Regular Factory====== ");
		FurnitureFactory factory = new RegularFurnitureFactory();

		Chair chair = factory.createChair();
		Table table = factory.createTable();
		Sofa sofa = factory.createSofa();

		chair.display();
		table.display();
		sofa.display();

//		MODERN
		System.out.println("=======Moder Factory====== ");

		factory = new ModernFurnitureFactory();

		chair = factory.createChair();
		table = factory.createTable();
		sofa = factory.createSofa();

		chair.display();
		table.display();
		sofa.display();

	}

}
