package com.java.main;

public class EmailNotification implements Notification {

	@Override
	public void send() {
		System.out.println("Email sent");

	}

}
