package homework2_shape;

public class Ring extends Shape {
	
	private Point p;
	private double radius;
	
	
	Ring(Point x, double radius) {
		this.p = x;
		this.radius = radius;
		this.square = Math.PI * radius * radius;
	}
}
