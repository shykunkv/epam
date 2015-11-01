package epam;

import java.math.BigInteger;

import epam.Karatsuba;

public class Main {

	public static void main(String[] args) {
		//System.out.println("Size of byte: " + TypeSize.getByteSize());
		//System.out.println("Size of short: " + TypeSize.getShortSize());
		//System.out.println("Size of int: " + TypeSize.getIntSize());
		//System.out.println("Size of long: " + TypeSize.getLongSize());
		
		System.out.println(SetBit.setBit(0, 3));
		//System.out.println(SetBit.clearBit(33, 0));
		
		
		Karatsuba test = new Karatsuba();
		BigInteger a = new BigInteger("832468923648932");
		BigInteger b = new BigInteger("823857398233242");
		
		//System.out.println(test.mult(a, b).equals(a.multiply(b)));
	}

}
