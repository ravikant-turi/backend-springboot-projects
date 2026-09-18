package com.java.main;

public class NotificationFactory {

	public Notification sendNotification(String type) {

		if (type == "EMAIL") {
			return new EmailNotification();
		} else if (type == "SMS") {
			return new SMSNotifcation();
		} else if (type == "WAPP") {
			return new WhatsAppNotification();
		}
		return null;
	}

}
