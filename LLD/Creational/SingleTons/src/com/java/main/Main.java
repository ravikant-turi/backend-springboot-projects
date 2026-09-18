package com.java.main;

public class Main {
	public static void main(String[] args) {
//		SingletonMain s1 = SingletonMain.getInstance();
//		SingletonMain s2 = SingletonMain.getInstance();
//
//		SingletonMain s3 = SingletonMain.getInstance();
//
//		System.out.println(s1.hashCode());
//		System.out.println(s2.hashCode());
//		System.out.println(s3.hashCode());
		
		
		SingletonMainSynchronized synchronized1=SingletonMainSynchronized.getInstance();
		System.out.println(synchronized1.getInstance());
	}

}
