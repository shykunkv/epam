package homework2_shape;

public class Triangle extends Shape {
	
	private Point p1;
	private Point p2;
	private Point p3;
	
	
	Triangle(Point p1, Point p2, Point p3) {
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		
		
		double a = Math.sqrt((p1.getY() - p1.getX()) * (p1.getY() - p1.getX()) + (p2.getY() - p2.getX()) * (p2.getY() - p2.getX()));
		double b = Math.sqrt((p1.getY() - p1.getX()) * (p1.getY() - p1.getX()) + (p3.getY() - p3.getX()) * (p3.getY() - p3.getX()));
		double c = Math.sqrt((p2.getY() - p2.getX()) * (p2.getY() - p2.getX()) + (p3.getY() - p3.getX()) * (p3.getY() - p3.getX()));
		
		double p = (a + b + c) / 2;
		
		this.square = Math.sqrt(p * (p - a) * (p - b) * (p - c));
	}

}
