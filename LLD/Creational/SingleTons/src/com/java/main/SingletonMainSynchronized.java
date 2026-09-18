package com.java.main;

public class SingletonMainSynchronized {

	private static SingletonMainSynchronized instance;

	private SingletonMainSynchronized() {
		System.out.println("Singleton Object Created 2");
	}

	public static SingletonMainSynchronized getInstance() {

		if (instance == null) {
			synchronized (SingletonMainSynchronized.class) {

				if (instance == null) {

					System.out.println("single object create 1");
					instance = new SingletonMainSynchronized();
				}
			}

		}
			return instance;

	}
}