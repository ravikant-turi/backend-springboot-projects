package com.java.main;

public class PaymentFactory {

	public Payment makePayment(String paymentMethod) {

		if (paymentMethod.equalsIgnoreCase("UPI")) {
			return new UPIPayment();
		} else if (paymentMethod.equalsIgnoreCase("CASH")) {
			return new CASHPayment();
		} else if (paymentMethod.equalsIgnoreCase("CARD")) {
			return new CARDPayment();
		} else {
			System.out.println("PAYMENT FAILED ");
			return null;
		}
	}

}
