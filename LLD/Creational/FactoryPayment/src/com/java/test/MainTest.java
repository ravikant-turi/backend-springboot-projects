package com.java.test;

import com.java.main.Payment;
import com.java.main.PaymentFactory;

public class MainTest {

	public static void main(String[] args) {

		PaymentFactory factory = new PaymentFactory();

		Payment paymentByUPI = factory.makePayment("UPI");
		paymentByUPI.pay();
		Payment paymentByCash = factory.makePayment("cAsh");
		paymentByCash.pay();
		Payment paymentByCard = factory.makePayment("CarD");
		paymentByCard.pay();

	}

}
