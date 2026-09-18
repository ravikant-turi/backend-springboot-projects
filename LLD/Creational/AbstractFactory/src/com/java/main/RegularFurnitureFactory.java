package com.java.main;

public class RegularFurnitureFactory implements FurnitureFactory {

	@Override
	public Sofa createSofa() {
		return new RegularSofa();
	}

	@Override
	public Table createTable() {
		return new RegularTable();
	}

	@Override
	public Chair createChair() {
		return new RegularChair();
	}

}
