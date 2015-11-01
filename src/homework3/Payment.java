package homework3;

import java.util.ArrayList;
import java.util.List;



public class Payment {
	
	class Product {
		
		private String name;
		private double price;
		private int quantity;
		
		public Product(String name, double price, int quantity) {
			this.name = name;
			this.price = price;
			this.quantity = quantity;
		}


			
		
		public int getQuantity() {
			return quantity;
		}




		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}




		public String getName() {
			return name;
		}


		public void setName(String name) {
			this.name = name;
		}


		public double getPrice() {
			return price;
		}


		public void setPrice(double price) {
			this.price = price;
		}		
	}
	
	
	List<Product> order = new ArrayList<Product>();
	
	public void addProduct(Product product) {
		order.add(product);
	}
	
	public void deleteProduct(Product product) {
		order.remove(product);
	}
	
	public void printOrder() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("-----------------------------------------------------------\n");
		sb.append("№\tName\t\tQuantity\tPrice\n");
		sb.append("-----------------------------------------------------------\n");
		
		int n = 1;
		double total = 0;
		for (Product p : order) {
			sb.append("" + n++);
			sb.append("\t" + p.getName());
			sb.append("\t" + p.getQuantity());
			sb.append("\t\t" + p.getPrice() * p.getQuantity() + "$");
			
			total += p.getPrice() * p.getQuantity();
			sb.append("\n");
		}
		sb.append("\n\n");
		sb.append("\t\t\t\tTotal: " + total + "$\n");

		sb.append("-----------------------------------------------------------\n");
		
		
		System.out.println(sb.toString());
	}
	
}
