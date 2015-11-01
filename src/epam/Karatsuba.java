package epam;

import java.math.BigInteger;


/**
 * TEST COMMIT
 * */
public class Karatsuba {
	
	
	public static int STOP_LENGTH = 64;
	
	public static BigInteger mult(BigInteger a, BigInteger b) { 
		BigInteger result = new BigInteger("0"); 
		int maxLen = Math.max(a.bitLength(), b.bitLength()); 
		
		if (maxLen < STOP_LENGTH) { // stop recursion and return simple A*B
			return a.multiply(b); 
		} 
		
		//lengths of new parts
		int N = maxLen / 2 + maxLen % 2;
		
		
		//x = a + b * 2 ^ length 
		//y = c + d * 2 ^ length
		
		/*
		 * Perform 	A = (A1 + A2 * (2^N))
		 * 			B = (B1 + B2 * (2^N))	
		 * */
		BigInteger A2 = a.shiftRight(N); 
		BigInteger B2 = b.shiftRight(N); 
		BigInteger A1 = a.subtract(A2.shiftLeft(N)); 
		BigInteger B1 = b.subtract(B2.shiftLeft(N)); 
		
		
		/*
		 * 				A * B = (A1 + A2*2^n) * (B1 + B2*2^n)  = A1*B1 + A2*B2*2^m + 2^n * (A1*B2 + B1*A2)	(1)
		 * 		Where:
		 * 				(A1*B2 + B1*A2) = A*B - (A1*B1 + A2*B2*2^m)											(2)
		 * 		Aftre (2) -> (1):
		 * 				A * B = A1*B1 + A2*B2*2^m + 2^n * (A1 + A2*2^n) * (B1 + B2*2^n)
		 * 
		 * */
		BigInteger A1B1 = mult(A1, B1); 
		BigInteger A2B2 = mult(A2, B2); 
		BigInteger AB = mult(A1.add(A2), B1.add(B2)); 
		
		result = A1B1.add(AB.subtract(A1B1).subtract(A2B2).shiftLeft(N)).add(A2B2.shiftLeft(N + N));
		return result; 
	}
}
