package epam;

public class TypeSize {
	
	public static byte getByteSize() {
		byte res = 0;
		byte b = 1;
		while (b != 0) {
			b <<= 1;
			res++;
		}
		return res;
	}
	
	public static byte getShortSize() {
		byte res = 0;
		short b = 1;
		while (b != 0) {
			b <<= 1;
			res++;
		}
		return res;
	}
	
	public static byte getIntSize() {
		byte res = 0;
		int b = 1;
		while (b != 0) {
			b <<= 1;
			res++;
		}
		return res;
	}
	
	public static byte getLongSize() {
		byte res = 0;
		Integer b = 1;
		while (b != 0) {
			b <<= 1;
			res++;
		}
		return res;
	}
	
}
