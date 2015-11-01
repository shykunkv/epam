package homework2_shape;

public class Main {

	public static void main(String[] args) {
		
		Shape s = new Triangle(new Point(1, 1), new Point(1, 0), new Point(0, 0));
		System.out.println(s.getSquare());
		
	}

}
