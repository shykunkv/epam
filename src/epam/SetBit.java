package epam;

public class SetBit {
	public static int setBit(int num, int pos) {
		return num | (1 << pos);
	}
	
	public static int clearBit(int num, int pos) {
		return num & ~(1 << pos);
	}
}
