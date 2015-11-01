package homework3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class Payment {
	class Product {
		private String name;
		private double price;
		
		
		public Product(String name, double price) {
			this.name = name;
			this.price = price;
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
	
	
	Map<Product, Integer> order = new HashMap();
	
	public void addProduct(Product product, int quantity) {
		order.put(product, quantity);
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
		for (Product p : order.keySet()) {
			sb.append("" + n++);
			sb.append("\t" + p.getName());
			sb.append("\t" + order.get(p));
			sb.append("\t\t" + p.getPrice() * order.get(p) + "$");
			
			total += p.getPrice() * order.get(p);
			sb.append("\n");
		}
		sb.append("\n\n");
		sb.append("\t\t\t\tTotal: " + total + "$\n");

		sb.append("-----------------------------------------------------------\n");
		
		
		System.out.println(sb.toString());
	}
	
}
