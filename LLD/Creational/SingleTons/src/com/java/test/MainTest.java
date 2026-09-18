package com.java.test;

import com.java.main.SingletonMainSynchronized;

public class MainTest {

	public static void main(String[] args) {
		
		SingletonMainSynchronized s1=SingletonMainSynchronized.getInstance();
		SingletonMainSynchronized s2=SingletonMainSynchronized.getInstance();
		
		System.out.println(SingletonMainSynchronized.getInstance().hashCode());
		System.out.println(s2.hashCode());
		System.out.println(s1.hashCode());
	}
	
}
