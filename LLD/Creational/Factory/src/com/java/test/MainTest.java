package com.java.test;

import com.java.main.Notification;
import com.java.main.NotificationFactory;

public class MainTest {

	public static void main(String[] args) {

		NotificationFactory factory = new NotificationFactory();

		Notification notification1 = factory.sendNotification("EMAIL");
		notification1.send();

		Notification notification2 = factory.sendNotification("WAPP");
		notification2.send();

		notification2 = factory.sendNotification("SMS");
		notification2.send();
	}

}
