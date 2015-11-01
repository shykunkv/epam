package homework3;

import homework3.Payment.Product;

public class Main {

	public static void main(String[] args) {
		Payment payment = new Payment();
		payment.addProduct(payment.new Product("Iphone 5S", 1999), 5);
		payment.addProduct(payment.new Product("Iphone 6", 2999), 5);
		payment.addProduct(payment.new Product("Iphone 6S", 3999), 5);
		
		payment.printOrder();
	}

}
