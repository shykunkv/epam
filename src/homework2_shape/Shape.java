package homework2_shape;

public class Shape {
	
	protected double square = 0;
	
	public double getSquare() {
		return square;
	}
	
	public double add(Shape shape) {
		return this.square + shape.getSquare();
	}
}
