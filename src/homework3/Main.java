package homework3;


public class Main {

	public static void main(String[] args) {
		Payment payment = new Payment();
		
		payment.addProduct(payment.new Product("Iphone 5S", 1999, 1));
		payment.addProduct(payment.new Product("Iphone 6", 2999, 2));
		payment.addProduct(payment.new Product("Iphone 6S", 3999, 3));
		
		payment.printOrder();
	}

}
