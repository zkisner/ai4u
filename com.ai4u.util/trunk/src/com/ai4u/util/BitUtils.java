/**
 * Created on 13/06/09
 */
package com.ai4u.util;

/**
 * @author kreich
 */
public class BitUtils {

	/**
	 * @param n The number to print.
	 * @return A String representation of the bits in the number.
	 */
	public static String toBitString(long n) {
		StringBuilder b = new StringBuilder();
		long mask = 0x8000000000000000L;
		for (int i = 0; i < Long.SIZE; i++) {
			b.append((n&mask) != 0 ? '1' : '0');
			mask >>>= 1;
		}
		return b.toString();
	}
	
}
